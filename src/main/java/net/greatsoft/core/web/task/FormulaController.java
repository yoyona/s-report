/*
 * 版权所有 2019 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.web.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.greatsoft.core.domain.mapper.FormulaMapper;
import net.greatsoft.core.service.task.FormulaService;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;

/**
 * 合理化建议功能相关
 * @since 2019-2-1
 */
@RestController
@RequestMapping(value="/formula")
public class FormulaController extends BaseController{
	
	@Autowired
	private FormulaMapper formulaMapper;
	
	@Autowired
	private FormulaService formulaService;
	
	/**
	 * 查询公式字典表
	 * @param map
	 * @return
	 * @since 2019-2-2
	 */
	@PostMapping(value="/list")
	public ResultDto getFormulaList(@RequestBody Map<String, Object> map){
		List<Map<String,Object>> datas = formulaMapper.getFormulaList(map);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", datas);
	}
	
	@PostMapping(value="/data")
	public ResultDto getFormulaDatas(@RequestBody Map<String, Object> map){
		String type = (String)map.get("type");
		String taskId = (String)map.get("taskId");
		String perId = (String)map.get("perId");
		//List<String> orgIds = (List)map.get("orgIds");
//		orgIds.add("4019302841307210");
//		orgIds.add("4887388333503220");
		String orgId = (String)map.get("orgId");
		//type = "0202";
		//perId = "2018----";
		String formulaId = (String)map.get("formulaId");
		Map<String,Object> param = new HashMap();
		param.put("formulaId", formulaId);
		List<Map<String,Object>> indices = formulaMapper.getFormulaDetail(param);
		List<Map<String,Object>> datas = formulaService.getFormulaData(type,taskId, perId, orgId, formulaId, indices);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("indices", indices);
		result.put("datas", datas);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 核实性说明导出excel
	 * @param type
	 * @param taskId
	 * @param perId
	 * @param response
	 * @since 2019-2-14
	 */
	@GetMapping(value="/exportExcel/{type}/{taskId}/{perId}/{orgId}")
	public void exportExcel(@PathVariable String type,@PathVariable String taskId,@PathVariable String perId,@PathVariable String orgId, HttpServletResponse response){
//		String type = (String)map.get("type");
//		String taskId = (String)map.get("taskId");
//		String perId = (String)map.get("perId");
//		List<String> orgIds = new ArrayList();//(List)map.get("orgIds");
//		orgIds.add("4019302841307210");
//		orgIds.add("4887388333503220");
//		type = "0202";
//		perId = "2018----";
		formulaService.exportExcel(type, taskId, perId, orgId, response);
	}
}
