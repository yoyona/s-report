/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.web.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.greatsoft.core.domain.mapper.AnalysisMapper;
import net.greatsoft.core.domain.mapper.QueryParamIndRelMapper;
import net.greatsoft.core.domain.model.system.CustomSummary;
import net.greatsoft.core.domain.model.system.Dic;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.task.ParamInds;
import net.greatsoft.core.domain.model.task.QueryParam;
import net.greatsoft.core.repository.system.DicRepository;
import net.greatsoft.core.repository.task.QueryParamRepository;
import net.greatsoft.core.service.system.OrgService;
import net.greatsoft.core.service.task.AnalysisService;
import net.greatsoft.core.service.task.TaskExecuteService;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;

/**
 * 数据分析相关
 * @since 2018-10-25
 */
@RestController
@RequestMapping(value="/analysis")
public class AnalysisController extends BaseController{
	@Autowired
	private TaskExecuteService taskExecuteService;
	
	@Autowired
	private DicRepository dicRepository;
	
	@Autowired
	private AnalysisMapper analysisMapper;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private QueryParamRepository queryParamRepository;
	
	@Autowired
	private QueryParamIndRelMapper queryParamIndRelMapper;
	
	@Autowired
	private AnalysisService analysisService;
	
	/**
	 * 查询当前年度数据
	 * @param map
	 * @return
	 * @since 2018-10-23
	 */
	@PostMapping(value="/queryCurrentYearDatas")
	public ResultDto queryCurrentYearDatas(@RequestBody Map<String, Object> map){
		String orgId = (String)map.get("orgId");
		List<String> orgIds = (List<String>)map.get("orgIds");
		List<String> sumOrgIds = (List<String>)map.get("sumOrgIds");
		List<String> indiceIds = (List<String>)map.get("indiceIds");
		List<String> summaryIndiceIds = (List<String>)map.get("summaryIndiceIds");
		String perId = String.valueOf(map.get("perId"));
		Map<String, Object> datas = analysisService.queryCurrentYearDatas(orgIds, sumOrgIds, indiceIds, summaryIndiceIds, perId);
		if(datas == null){
			return new ResultDto(ResultDto.CODE_FAIL, "请确认当前任务选择是否正确","");
		}
		return new ResultDto(ResultDto.CODE_SUCCESS, "", datas);
	}
	
	/**
	 * 查询参数列表
	 * @param map
	 * @return
	 * @since 2018-11-16
	 */
	@PostMapping(value="/queryAnalysisParams")
	public ResultDto queryAnalysisParams(@RequestBody Map<String, Object> map){
		/*List<Dic> fillTypes = dicRepository.queryDicsByType("t5");//填报类型
		List<Dic> levels = dicRepository.queryDicsByType("t6");//机构级别
		result.put("fillTypes", fillTypes);
		result.put("levels", levels);*/
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> datas = analysisMapper.queryTablesName(map);
		result.put("tables", datas);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	/**
	 * 查询表名列表
	 * @param map
	 * @return
	 * @since 2018-11-16
	 */
	@PostMapping(value="/queryTablesName")
	public ResultDto queryTablesName(@RequestBody Map<String, Object> map){
		List<Map<String, Object>> datas = analysisMapper.queryTablesName(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("datas", datas);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	/**
	 * 查询行信息列表
	 * @param map
	 * @return
	 * @since 2018-11-16
	 */
	@PostMapping(value="/queryRowsName")
	public ResultDto queryRowsName(@RequestBody Map<String, Object> map){
		List<Map<String, Object>> datas = analysisMapper.queryRowsName(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("datas", datas);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询列信息列表
	 * @param map
	 * @return
	 * @since 2018-11-16
	 */
	@PostMapping(value="/queryColumnsName")
	public ResultDto queryColumnsName(@RequestBody Map<String, Object> map){
		List<Map<String, Object>> datas = analysisMapper.queryColumnsName(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("datas", datas);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	/**
	 * 查询机构树
	 * @param map
	 * @return
	 * @since 2018-11-16
	 */
	@PostMapping(value="/queryOrgTree")
	public ResultDto queryOrgTree(@RequestBody Map<String, Object> map){
		List<Org> datas = orgService.showTree(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("datas", datas);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询指标解读列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 * @since 2018-11-16
	 */
	@PostMapping(value="/queryIndices/{pageNo}/{pageSize}")
	public ResultDto queryIndices(@RequestBody Map<String, Object> map,@PathVariable Integer pageNo, @PathVariable Integer pageSize, HttpServletRequest request){
		Page<CustomSummary> page = PageHelper.startPage(pageNo, pageSize);
		List<Map<String,Object>> inds = analysisMapper.queryIndices(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("inds", inds);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize", page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询数据查询列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 * @since 2018-12-6
	 */
	@PostMapping(value="/getQueryParam/{pageNo}/{pageSize}")
	public ResultDto getQueryParam(@RequestBody Map<String, Object> map,@PathVariable Integer pageNo, @PathVariable Integer pageSize, HttpServletRequest request){
		Page<CustomSummary> page = PageHelper.startPage(pageNo, pageSize);
		List<Map<String,Object>> list = analysisMapper.getQueryParam(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize", page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 增加数据查询条件,以parId区分新增还是修改
	 * @param map
	 * @return
	 * @since 2018-12-7
	 */
	@PostMapping(value="/saveQueryParams")
	@Transactional
	public ResultDto addQueryParams(@RequestBody Map<String, Object> map){
		List<Map> indiceIds = (List<Map>)map.get("indiceIds");
		String parName = (String)map.get("parName");
		String des = (String)map.get("parDes");
		String orgId = (String)map.get("orgId");
		String userId = (String)map.get("userId");
		String parId = (String)map.get("parId");
		String isPublic = (String)map.get("isPublic");
		QueryParam qp = new QueryParam();
		qp.setParName(parName);
		qp.setOrgId(orgId);
		qp.setCreateUserId(userId);
		qp.setDes(des);
		qp.setIsPublic(isPublic);
		//parId不为空则为修改操作
		if(parId != null){
			qp.setId(parId);
		}else{
			qp.setCreateTime(new Date());
		}
		qp = queryParamRepository.save(qp);
		//修改时先删除原来的对应关系
		if(parId != null){
			queryParamIndRelMapper.deleteQueryParamIndRelByParId(parId);
		}
		List<ParamInds> piList = new ArrayList();
		ParamInds pi = null;
		Map ind = null;
		for (int i = 0; i < indiceIds.size(); i++) {
			ind = indiceIds.get(i);
			pi = new ParamInds();
			pi.setIndId((String)ind.get("ID"));
			pi.setParId(qp.getId());
			pi.setOrder((Integer)ind.get("ORDER"));
			piList.add(pi);
		}
		queryParamIndRelMapper.insertQueryParamIndRel(piList);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", qp);
	}
	
	/**
	 * 删除一览表查询信息
	 * @param map
	 * @return
	 * @since 2018-12-14
	 */
	@PostMapping(value="/deleteQueryParams")
	@Transactional
	public ResultDto deleteQueryParams(@RequestBody Map<String, Object> map){
		String parId = (String)map.get("parId");
		QueryParam qp = new QueryParam();
		qp.setId(parId);
		queryParamRepository.delete(qp);
		queryParamIndRelMapper.deleteQueryParamIndRelByParId(parId);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", "");
	}
	
	/**
	 * 根据id查询一览表信息及与指标对应关系
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 * @since 2018-12-7
	 */
	@PostMapping(value="/getQueryParamById")
	public ResultDto getQueryParamById(@RequestBody Map<String, Object> map, HttpServletRequest request){
		String parId = (String)map.get("parId");
		Map<String,Object> result = analysisService.getQueryParamById(parId);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
}
