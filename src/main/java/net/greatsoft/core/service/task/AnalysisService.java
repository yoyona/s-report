/*
 * 版权所有 2019 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.service.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.greatsoft.core.domain.mapper.AnalysisMapper;
import net.greatsoft.core.domain.mapper.QueryParamIndRelMapper;
import net.greatsoft.core.domain.mapper.TaskExecuteMapper;
import net.greatsoft.core.domain.model.task.Period;
import net.greatsoft.core.domain.model.task.QueryParam;
import net.greatsoft.core.repository.task.PeriodRepository;
import net.greatsoft.core.repository.task.QueryParamRepository;

/**
 * TODO 填写描述信息
 * @since 2019-1-4
 */
@Service
public class AnalysisService {
	//系统年份
	@Value("${system.year}")
	private String year;
	
	@Autowired
	private AnalysisMapper analysisMapper;
	
	@Autowired
	private QueryParamRepository queryParamRepository;
	
	@Autowired
	private QueryParamIndRelMapper queryParamIndRelMapper;
	
	@Autowired
	private PeriodRepository periodRepository;
	
	/**
	 * 查询年度填报数据
	 * @param orgIds
	 * @param indiceIds
	 * @return
	 * @since 2018-10-23
	 */
	public Map<String, Object> queryCurrentYearDatas(List<String> orgIds,List<String> sumOrgIds,List<String> indiceIds,List<String> summaryIndiceIds,String perId) {
		Map<String, Object> dataMap = new HashMap();
		Period per = periodRepository.findPeriodByPeriodId(Long.valueOf(perId));
		perId = per.getParamName();
		
		if(orgIds.size() > 0 && indiceIds.size() > 0){
			List<Map<String,Object>> data = new ArrayList(); 
			if(orgIds.size() > 999){
				List<Map<String,Object>> d = null;
				int num = orgIds.size()/999 == 0?orgIds.size()/999:(orgIds.size()/999+1);
				for (int i = 0; i < num; i++) {
					int length = (i+1) * 999 < orgIds.size()?(i+1) * 999:orgIds.size();
					d = this.getAnalysisData(per.getTaskId(), perId, "0", orgIds.subList(i* 999, length), indiceIds, "STA_TABLE", "STA_COLUMN");
					data.addAll(d);
				}
			}else{
				data = this.getAnalysisData(per.getTaskId(), perId, "0", orgIds, indiceIds, "STA_TABLE", "STA_COLUMN");
			}
			dataMap.put("dataList", data);
		}
		if(sumOrgIds.size() > 0 && summaryIndiceIds.size() > 0){
			List<Map<String,Object>> sumdata = new ArrayList();
			if(sumOrgIds.size() > 999){
				List<Map<String,Object>> d = null;
				int num = sumOrgIds.size()/999 == 0?sumOrgIds.size()/999:(sumOrgIds.size()/999+1);
				for (int i = 0; i < num; i++) {
					int length = (i+1) * 999 < sumOrgIds.size()?(i+1) * 999:sumOrgIds.size();
					d = this.getAnalysisData(per.getTaskId(), perId, "1", sumOrgIds.subList(i* 999, length), summaryIndiceIds, "STA_TABLE", "STA_COLUMN");
					sumdata.addAll(d);
				}
			}else{
				sumdata = this.getAnalysisData(per.getTaskId(), perId, "1", sumOrgIds, summaryIndiceIds, "STA_TABLE", "STA_COLUMN");
			}
			dataMap.put("summaryDataList", sumdata);
		}
		return dataMap;
	}
	
	public List<Map<String, Object>> getAnalysisData(String taskId,String perId,String isSummary,List orgIdsFor,List indiceIds,String tableNameFor,String columnNameFor){
		StringBuffer sbIndiceIds  = new StringBuffer();
		for (int i = 0; i < indiceIds.size(); i++) {
			sbIndiceIds.append("','").append(indiceIds.get(i));
		}
		String indIds = sbIndiceIds.toString().replaceFirst("',", "") + "'";
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("year", year);
		param.put("taskId", taskId);
		param.put("isSummary", isSummary);
		param.put("indiceIds", indIds);
		List<Map<String, Object>> indices = this.analysisMapper.queryStandardIndicesAnalysis(param);
		if(indices.size() == 0){
			return null;
		}
		
		Map<String,Object> tables = new HashMap();
		Map<String,Object> tables0101 = new HashMap();
		StringBuffer sbOrgIds  = new StringBuffer();
		for (int i = 0; i < orgIdsFor.size(); i++) {
			sbOrgIds.append("','").append(orgIdsFor.get(i));
		}
		Map<String, Object> indice = null;
		String columns = "";
		String columns0101 = "";
		String oldColumns = "";
		String oldColumns0101 = "";
		for (int i = 0; i < indices.size(); i++) {
			indice = indices.get(i);
			if(!tables.containsKey((String)indice.get(tableNameFor))){
				if(((String)indice.get(tableNameFor)).contains("0101")){
					columns = (String)indice.get(columnNameFor) + " as "+(String)indice.get(tableNameFor) + "_" + (String)indice.get(columnNameFor) + "_" + (String)indice.get("STA_NAME");
					columns0101 = (String)indice.get(columnNameFor) + " as "+(String)indice.get(tableNameFor) + "_" + (String)indice.get(columnNameFor);
					tables0101.put((String)indice.get(tableNameFor), columns0101);
				}else{
					columns = (String)indice.get(columnNameFor) + " as "+(String)indice.get(tableNameFor) + "_" + (String)indice.get(columnNameFor);
				}
				tables.put((String)indice.get(tableNameFor), columns);
			}else{
				oldColumns = (String)tables.get((String)indice.get(tableNameFor));
				if(((String)indice.get(tableNameFor)).contains("0101")){
					oldColumns0101 = (String)tables0101.get((String)indice.get(tableNameFor));
					columns = oldColumns + "," + (String)indice.get(columnNameFor) + " as " + (String)indice.get(tableNameFor) + "_" +(String)indice.get(columnNameFor) + "_" + (String)indice.get("STA_NAME");
					columns0101 = oldColumns0101 + "," + (String)indice.get(columnNameFor) + " as " + (String)indice.get(tableNameFor) + "_" +(String)indice.get(columnNameFor);
					tables0101.put((String)indice.get(tableNameFor), columns0101);
				}else{
					columns = oldColumns + "," + (String)indice.get(columnNameFor) + " as " + (String)indice.get(tableNameFor) + "_" +(String)indice.get(columnNameFor);
				}
				tables.put((String)indice.get(tableNameFor), columns);
			}
		}
	
		StringBuffer sbTable = new StringBuffer();
		StringBuffer sbOn = new StringBuffer();
		StringBuffer showColumns = new StringBuffer();
		String showColumnsFirst = " t0.org_id,t0.org_name,t0.ORG_ADMINISTRATIVE_CODE,t0.ORG_FILL_TYPE,t0.ORG_CATEGORY,t0.ORG_BUDGET_MANAGEMENT_LEVEL,t0.ORG_HOSPITAL_GRADE";
		int t = 0;
		for (String key : tables.keySet()) { 
		  columns = (String)tables.get(key);
		  
		  if(key.contains("0101")){
			  columns0101 = (String)tables0101.get(key);
			  String[] col011 = columns.split(",");
			  for (int i = 0; i < col011.length; i++) {
				  String[] collkx = col011[i].split("_");
				  String lkx = collkx[3];
				  int it = t++;
				  if("合计".equals(collkx[3])){
					  sbTable.append("( select o.org_level,o.org_id,o.org_name,o.ORG_ADMINISTRATIVE_CODE,o.ORG_FILL_TYPE,o.ORG_CATEGORY,o.ORG_BUDGET_MANAGEMENT_LEVEL,o.ORG_HOSPITAL_GRADE,").append(collkx[0] + "_" + collkx[1] + "_" + collkx[2] + "_" + lkx)
				  		 .append(" from org o")
				  		 .append(" left join ").append(key).append("@WLZBDATA t on t.userid_ = o.org_id ").append("and ").append("t.bbq_ = '").append(perId).append("'")
				  		 .append(" and ISHJ_ = 1")
				  		 .append(" where ").append("o.org_id in (")
				  		 .append(sbOrgIds.toString().replaceFirst("',",""))
				  		 .append("') ) t" + it);
				  }else{
					  sbTable.append("( select o.org_level,o.org_id,o.org_name,o.ORG_ADMINISTRATIVE_CODE,o.ORG_FILL_TYPE,o.ORG_CATEGORY,o.ORG_BUDGET_MANAGEMENT_LEVEL,o.ORG_HOSPITAL_GRADE,").append(collkx[0] + "_" + collkx[1] + "_" + collkx[2] + "_" + lkx)
				  		 .append(" from org o")
				  		 .append(" left join ").append(key).append("@WLZBDATA t on t.userid_ = o.org_id ").append("and ").append("t.bbq_ = '").append(perId).append("'")
				  		 .append(" and A10 = '").append(lkx).append("'")
				  		 .append(" where ").append("o.org_id in (")
				  		 .append(sbOrgIds.toString().replaceFirst("',",""))
				  		 .append("') ) t" + it);
				  }
				  
				  showColumns.append(",t").append(it).append(".").append(collkx[0]).append("_").append(collkx[1]).append("_").append(collkx[2]).append("_").append(collkx[3]);
				  if(t > 1){
					  sbTable.append(" on ").append("t").append(t-2).append(".org_id = t").append(t-1).append(".org_id");
				  }
				  if(t != indiceIds.size()){
					  sbTable.append(" full join ");
				  }
				  System.out.println("01-1表>>>>>> " + sbTable.toString());
			  }
		  }else{
			  /*if(sbTable.toString().length() > 0){
				  sbTable.append(" full join ");
			  }*/
			  showColumns.append(",t" + t + "." + columns.replaceAll(",", ",t" + t + "."));
			  sbTable.append("( select o.org_level,o.org_id,o.org_name,o.ORG_ADMINISTRATIVE_CODE,o.ORG_FILL_TYPE,o.ORG_CATEGORY,o.ORG_BUDGET_MANAGEMENT_LEVEL,o.ORG_HOSPITAL_GRADE,").append(columns)
		  		 .append(" from org o")
		  		 .append(" left join ").append(key).append("@WLZBDATA t on t.userid_ = o.org_id ").append("and ").append("t.bbq_ = '").append(perId).append("'")
		  		 .append(" where o.org_id in (")
		  		 .append(sbOrgIds.toString().replaceFirst("',",""))
		  		 .append("') ) t" + t++);
			  if(t > 1){
				  sbTable.append(" on ").append("t").append(t-2).append(".org_id = t").append(t-1).append(".org_id");
			  }
			  /*if(t != tables.keySet().size()){
				  sbTable.append(" full join ");
			  }*/
			  System.out.println(tables.keySet().size());
			  if(t != indiceIds.size()-1 && t != tables.keySet().size()){
				  sbTable.append(" full join ");
			  }
			  
		  }
		  
		  /*if(t > 1){
			  sbTable.append(" on ").append("t").append(t-2).append(".org_id = t").append(t-1).append(".org_id");
		  }
		  if(t != tables.keySet().size()){
			  sbTable.append(" full join ");
		  }*/
		}
		//格式例如：t0.D6 as IGLKB_B01_D6，最外部查询时将D6 as去掉
		String[] cols = showColumns.toString().replaceFirst(",", "").split(",");
		StringBuffer showCol = new StringBuffer();
		String col = "";
		for (int i = 0; i < cols.length; i++) {
			col = cols[i].split("\\.")[1];
			showCol.append("," + cols[i].split("\\.")[0] + "." + col.split("as")[1].replaceAll(" ", ""));
		}
		String orderby = " order by t0.org_level, t0.org_administrative_code, t0.org_budget_management_level, t0.org_id, t0.org_name";
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(showColumnsFirst + showCol.toString()).append(" from ").append(sbTable.toString()).append(sbOn.toString()).append(orderby);
		System.out.println(">>>>> " + sql.toString());
		param.put("sql", sql.toString());
		List<Map<String, Object>> object = this.analysisMapper.queryCurrentYearDatas(param);
		return object;
	}
	
	
	/**
	 * 原表查询明细信息
	 * @param parId
	 * @return
	 * @since 2019-1-4
	 */
	public Map<String,Object> getQueryParamById(String parId){
		QueryParam qp = queryParamRepository.findQueryParamById(parId);
		List<Map<String,Object>> piList = queryParamIndRelMapper.getQueryParamIndRelByParId(parId);
		Map<String,Object> piMap = new HashMap<>();
		Map pi = null;
		Map node = null;
		//组织已选指标层级结构
		for (int i = 0; i < piList.size(); i++) {
			pi = piList.get(i);
			/*//表名
			if(!piMap.containsKey((String) pi.get("STA_TABLENAME"))){
				node = new HashMap();
				node.put("ID", (String)pi.get("STA_TABLENAME"));
				node.put("NAME", (String)pi.get("STA_TABLENAME"));
				node.put("PID", 0);
				
				piMap.put((String) pi.get("STA_TABLENAME"), node);
			}
			//行名
			if(!piMap.containsKey((String)pi.get("STA_TABLENAME") + "_" + (String)pi.get("STA_ROWNAME"))){
				node = new HashMap();
				node.put("ID", (String)pi.get("STA_TABLENAME") + "_" + (String)pi.get("STA_ROWNAME"));
				node.put("NAME", (String)pi.get("STA_ROWNAME"));
				node.put("PID", (String)pi.get("STA_TABLENAME"));
				piMap.put((String)pi.get("STA_TABLENAME") + "_" + (String)pi.get("STA_ROWNAME"), node);
			}*/
			//具体指标
			if(!piMap.containsKey((String)pi.get("STA_TABLENAME") + "_" + (String)pi.get("STA_ROWNAME") + (String)pi.get("REL_INDID"))){
				node = new HashMap();
				node.put("ID", (String)pi.get("REL_INDID"));
				node.put("PID", (String)pi.get("STA_TABLENAME") + "_" + (String)pi.get("STA_ROWNAME"));
				node.put("NAME", pi.get("STA_NAME"));
				String hj = "";
				if(((String)pi.get("STA_TABLE")).contains("0101")){
					if(((String)pi.get("STA_NAME")).contains("_")){
						String[] indNames = ((String)pi.get("STA_NAME")).split("_");
						if(indNames[0].equals("合计")){
							hj = "合计";
							node.put("COLUMN", (String)pi.get("STA_TABLE") + "_" + (String)pi.get("STA_COLUMN") + "_" + hj);
						}else{
							node.put("COLUMN", (String)pi.get("STA_TABLE") + "_" + (String)pi.get("STA_COLUMN") + "_" + indNames[0]);
						}
					}
				}else{
					node.put("COLUMN", (String)pi.get("STA_TABLE") + "_" + (String)pi.get("STA_COLUMN"));
				}
				/*if("合计".equals(hj)){
					node.put("COLUMN", (String)pi.get("STA_TABLE") + "_" + (String)pi.get("STA_COLUMN") + "_" + hj);
				}else{
					node.put("COLUMN", (String)pi.get("STA_TABLE") + "_" + (String)pi.get("STA_COLUMN"));
				}*/
				
				node.put("ROWORDER", pi.get("STA_ROW_ORDER"));
				node.put("ORDER", pi.get("REL_INDID_ORDER"));
				piMap.put((String)pi.get("STA_TABLENAME") + "_" + (String)pi.get("STA_ROWNAME") + (String)pi.get("REL_INDID"), node);
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("inds", piMap.values());
		result.put("qp", qp);
		result.put("piList", piList);
		return result;
	}
}
