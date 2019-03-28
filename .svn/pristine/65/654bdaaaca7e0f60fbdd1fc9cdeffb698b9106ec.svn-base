package net.greatsoft.core.web;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.greatsoft.core.domain.mapper.AnalysisMapper;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.task.Attachment;
import net.greatsoft.core.domain.model.task.FileAdditional;
import net.greatsoft.core.domain.model.task.Period;
import net.greatsoft.core.repository.system.OrgRepository;
import net.greatsoft.core.repository.task.PeriodRepository;
import net.greatsoft.core.web.dto.ResultDto;
/**
 * 文件上传Controller
 * @author litian
 *
 */
@RestController
@RequestMapping(value="/file")
public class FileUploadController {
	//任务编辑新增上传文件临时文件夹
	@Value("${file.path.temp}")
	private String tempDir;
	//机构批复上传文件夹
	@Value("${file.path.orgreply}")
	private String  orgreplyPath;
	
	//数据接口上传文件临时文件夹
	@Value("${file.path.dataFile}")
	private String dataFilePath;
	
	//系统年份
	@Value("${system.year}")
	private String year;
	
	//报表期
//	@Value("${system.bbq}")
//	private String bbq;
	
	@Autowired
	private AnalysisMapper analysisMapper;
	
	@Autowired
	private OrgRepository orgRepository;
	
	@Autowired
	private PeriodRepository periodRepository;
	
	// 省级市级附件上传路径
	@Value("${file.path.attachment}")
	private String attachmentPath;
	
	/**
	 * 单文件上传(先上传到临时文件夹里面)
	 * @return
	 */
	@PostMapping(value="/singleFileUpload")
	public ResultDto singleFileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile multipartFile){
		// 判断大小是否满足条件
		if(multipartFile.getSize() > 52428800){
			return new ResultDto(ResultDto.TOKEN_FAIL,"文件过大",null);
		}
		UUID uuid = UUID.randomUUID();
		String filePath = tempDir + "/" + uuid;
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String fileUrl = filePath + suffix;
		File file = new File(fileUrl);
		if(!file.exists()){
			new File(fileUrl.substring(0, fileUrl.lastIndexOf("/"))).mkdirs();
		}
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileAdditional f = new FileAdditional();
		f.setFileUrl(fileUrl);
		f.setFileName(multipartFile.getOriginalFilename());
		f.setFileStatus("0");
		f.setCreateDate(new Date());
		f.setUuid(String.valueOf(uuid));
		return new ResultDto(ResultDto.CODE_SUCCESS,"",f);
	}
	/**
	 * 机构批复文件上传
	 * @param request
	 * @param multipartFile
	 * @return
	 */
	@PostMapping(value="/orgReplyFileUpload")
	public ResultDto orgReplyFileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile multipartFile){
		// 判断大小是否满足条件
		if(multipartFile.getSize() > 52428800){
			return new ResultDto(ResultDto.TOKEN_FAIL,"文件过大",null);
		}
		UUID uuid = UUID.randomUUID();
		String filePath = orgreplyPath + "/" + uuid;
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String fileUrl = filePath + suffix;
		File file = new File(fileUrl);
		if(!file.exists()){
			new File(fileUrl.substring(0, fileUrl.lastIndexOf("/"))).mkdirs();
		}
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileAdditional f = new FileAdditional();
		f.setFileUrl(fileUrl);
		f.setFileName(multipartFile.getOriginalFilename());
		f.setFileStatus("0");
		f.setCreateDate(new Date());
		f.setUuid(String.valueOf(uuid));
		return new ResultDto(ResultDto.CODE_SUCCESS,"",f);
	}
	
	/**
	 * 数据接口文件上传
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/dataFileUpload")
	public ResultDto recognizanceFileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile multipartFile,@RequestParam("orgId") String orgId,@RequestParam("uniqueId") String uniqueId,@RequestParam("periodId") String periodId) throws Exception{
		// 判断大小是否满足条件
		if(multipartFile.getSize() > 52428800){
			return new ResultDto(ResultDto.TOKEN_FAIL,"文件过大",null);
		}
		UUID uuid = UUID.randomUUID();
		String filePath = dataFilePath + "/" + uuid;
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String fileUrl = filePath + suffix;
		File file = new File(fileUrl);
		if(!file.exists()){
			new File(fileUrl.substring(0, fileUrl.lastIndexOf("/"))).mkdirs();
		}
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!file.exists()){
			return null;
		}
		
		/*********处理数据**********/
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(FileUtils.openInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String,Object>> inds = null;
		
		Period per = periodRepository.getPeriodByPeriodId(Long.valueOf(periodId));
		Map param = new HashMap();
		param.put("year", year);
		param.put("taskId", per.getTaskId());
		//报表期参数
		String bbq = per.getParamName();
		StringBuffer insertSql = null;
		StringBuffer table = null;
		StringBuffer col = null;
		StringBuffer val = null;
		//StringBuffer deleteTable = null;
		StringBuffer updateSql = null;
		StringBuffer querySql = null;
		String sheetId = "";
		String tableName = "";
		Map<String,Object> ind = null;
//		try {
			// 01-1表为浮动行表格，直接将数据导入到对应表中，无需维护查询指标表
			try {
				this.importFirsttable(wb.getSheet("B0101"),orgId,bbq);
			} catch (Exception e1) {
				System.out.println(e1);
				//throw new Exception(e1);
				String errorMsg = "B0101页数据错误，请修改后重新导入。";
				return new ResultDto(ResultDto.CODE_FAIL,"",errorMsg);
			}
			//查询sheet页id列表，分别组织数据
			List<Map<String,Object>> sheetIds = analysisMapper.queryIndsSheetIds(param);
			for (int i = 0; i < sheetIds.size(); i++) {
				try {
					sheetId = (String)sheetIds.get(i).get("STA_SHEETID");
					//获取sheet页，若导入文件中没有sheet页，则跳过，不影响其他页的导入
					HSSFSheet sheet = wb.getSheet(sheetId);
					if(sheet == null){
						continue;
					}
					param.put("sheetId", sheetId);
					inds = analysisMapper.queryIndsBySheetId(param);
					if(inds.size() == 0){
						continue;
					}
					tableName = (String)sheetIds.get(i).get("STA_TABLE");
					insertSql = new StringBuffer();
					table = new StringBuffer();
					col = new StringBuffer();
					val = new StringBuffer();
					updateSql = new StringBuffer();
					querySql = new StringBuffer();
					if(inds != null && inds.size() > 0){
						table.append("insert into ").append(tableName).append("@wlzbdata");
						updateSql.append("update ").append(tableName).append("@wlzbdata set ");
					}
					for (int j = 0; j < inds.size(); j++) {
						ind = inds.get(j);
						//获取sheet页中的行坐标
						HSSFRow row = sheet.getRow(Integer.valueOf((String)ind.get("STA_COL")));
						//获取sheet页中的列坐标
						HSSFCell value = row.getCell(Integer.valueOf((String) ind.get("STA_ROW")));
						if(j > 0){
							col.append(",");
							val.append(",");
							updateSql.append(",");
						}
						col.append(ind.get("STA_COLUMN"));
						//处理无值情况
						if(value == null || "".equals(value.toString())){
							val.append(0.00);
							updateSql.append(ind.get("STA_COLUMN")).append("=").append(0.00);
						}else{
							val.append(value.getNumericCellValue());
							//更新语句
							updateSql.append(ind.get("STA_COLUMN")).append("=").append(value.getNumericCellValue());
						}
					}
					//组织必要条件语句
					if(inds != null && inds.size() > 0){
						//btype_区分单户汇总表，（0是单户，1 是汇总）
						col.append(",userid_,bbq_,btype_");
						val.append(",'").append(uniqueId).append("','").append(bbq).append("',").append("0");
						//更新语句
						updateSql.append(",shtag_=0,shtag2_=0").append(" where userid_= '").append(uniqueId).append("' and bbq_='").append(bbq).append("' and btype_='").append("0'");
					}
					if(inds != null && inds.size() > 0){
						insertSql.append(table.toString()).append("(").append(col.toString()).append(") values (").append(val.toString()).append(")");
					}
					
					//组织查询语句
					querySql.append("select * from ").append(tableName).append("@wlzbdata").append(" where userid_='").append(uniqueId).append("' and bbq_='").append(bbq).append("'");
					param.put("querySql", querySql.toString());
					//组织删除语句
					//deleteTable.append("delete ").append(tableName).append("@wlzbdata").append(" where userid_='").append(uniqueId).append("' and bbq_='").append("2016").append("'");
					//param.put("deleteSql", deleteTable.toString());
					//System.out.println(">>>>>>>>>> " + querySql.toString());
					List<Map<String,Object>> datas = analysisMapper.queryDatas(param);
					
					if(datas != null && datas.size() > 0){
//						System.out.println(">>>>>>>>>> " + updateSql.toString());
						param.put("updateSql", updateSql.toString());
						//更新数据
						analysisMapper.updateDatas(param);
					}else{
//						System.out.println(">>>>>>>>>> " + insertSql.toString());
						param.put("insertSql", insertSql.toString());
						//删除历史数据
						//analysisMapper.deleteDatas(param);
						//插入导入数据
						analysisMapper.insertDatas(param);
					}
				} catch (Exception e) {
					e.printStackTrace();
					String errorMsg = "导入excel中，" + sheetId + "页数据错误，请修改后重新导入";
					return new ResultDto(ResultDto.CODE_FAIL,"",errorMsg);
				}
			}
			return new ResultDto(ResultDto.CODE_SUCCESS,"",null);
	}
	
	public void importFirsttable(HSSFSheet sheet,String orgId,String bbq){
		Org org = orgRepository.getOrgById(orgId);
		String table = "";
		if("01".equals(org.getFileType().substring(0, 2)) || "03".equals(org.getFileType().substring(0, 2))){//卫生
			table = "IWSBB_B0101";
		}else if(org.getFileType().length() > 4 && "0201".equals(org.getFileType().substring(0, 4))){//医院
			table = "IGLBB_B0101";
		}else if(org.getFileType().length() > 4 && "0202".equals(org.getFileType().substring(0, 4))){//基层
			table = "IJCBB_B0101 ";
		}
		//获取最大行
		int MaxRow = sheet.getPhysicalNumberOfRows();
		//循环遍历信息()
		HSSFRow row=null;
		StringBuffer insert011Sql = null;
		//初始化合计行参数
		String isHj = "'','','',1";
		String cols ="bbq_,userid_,btype_,db2index_,shtag_,shtag2_,option_,ishj_,a10,b10,c10,d10,e10,f10,g10,h10,i10,j10,k10,l10,m10,n10,o10,p10,q10,r10,s10,t10,u10,v10,w10,x10,y10,z10,aa10,ab10";
		StringBuffer tj = new StringBuffer();
		tj.append("insert into ").append(table).append("@wlzbdata").append(" (").append(cols).append(") values(");
		tj.append("'").append(bbq).append("'").append(",'").append(orgId).append("',").append(0).append(",").append(new Date().getTime()).append(",");//.append("0,0,0,");
		Map param = new HashMap();
		if(MaxRow > 8){
			param.put("deleteSql", "delete from " + table + "@wlzbdata where bbq_='" + bbq + "' and userid_ = '" + orgId + "'");
			analysisMapper.deleteDatas(param);
		}
		for (int i = 8; i < MaxRow; i++) {
			row = sheet.getRow(i);
			//跳过隐藏行
			if(row.getZeroHeight()==true){
				continue;
			}
			insert011Sql = new StringBuffer();
			//特殊处理表格最后一行，若最后一行第二列无值，则认为表格数据结束
			Cell cell1 = row.getCell(1);
			if(i > 8 && ("".equals(getCellValue(cell1)) || getCellValue(cell1) == null)){
				break;
			}
			String lkx = getCellValue(row.getCell(0));
			//类，款（长度是3,5）：SHTAG_,SHTAG2_是空值；OPTION_是1；ISHJ_是0；
			//项（长度是7）：SHTAG_,SHTAG2_是0（代表审核不通过）；OPTION_是空值；ISHJ_是0；
			if(i > 8){
				if(lkx.length() == 3 || lkx.length() == 5){
					isHj = "'','',1,0";
				}else if(lkx.length() == 7){
					isHj = "0,0,'',0";
				}
			}
			insert011Sql.append(tj.toString()).append(isHj);
			//获取到Excel文件中的所有的列 
			int cells = row.getPhysicalNumberOfCells();
			//遍历该行的所有单元格
			for (int j = 0; j < cells; j++) {
				//获取到列的值
				Cell cell = row.getCell(j);
				//合计行前两格数据为空
				if(i == 8 && (j == 0 || j == 1)){
					insert011Sql.append(",''");
					continue;
				}
				// 获取单元格里面的值
				if(j == 0){
					insert011Sql.append(",").append(getCellValue(cell));
				}else{
					if(j == 1){
 						insert011Sql.append(",'").append(getCellValue(cell)).append("'");
					}else{
						insert011Sql.append(",").append(getCellValue(cell).equals("")?"0.00":new BigDecimal(getCellValue(cell)).toString());
					}
				}
			}
			insert011Sql.append(")");
			
//			System.out.println("insert01-1表>>>>>>>>>>>>>> " + insert011Sql.toString());
			param.put("insertSql", insert011Sql.toString());
			analysisMapper.insertDatas(param);
		}
	}
	
	public String getCellValue(Cell cell){     
   	 //FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
        if(cell == null) return "";     
             
        if(cell.getCellType() == Cell.CELL_TYPE_STRING){     
                 
            return cell.getStringCellValue().trim();     
                 
        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){     
                 
            return String.valueOf(cell.getBooleanCellValue()).trim();     
                 
        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){     
       	 /*try {  
       	        return String.valueOf(cell.getStringCellValue());  
       	 } catch (IllegalStateException e) {  
       	        return  String.valueOf(cell.getNumericCellValue());  
       	 }  */
       	/* CellValue evaluate = evaluator.evaluate(cell);
       	 return  evaluate.toString();*/
       	 cell.setCellType(Cell.CELL_TYPE_NUMERIC); //设置其单元格类型为数字  
       	  return String.valueOf(cell.getNumericCellValue());
       	 
        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){     
                 
            return String.valueOf(cell.getNumericCellValue()).trim();     
                 
        }
        return "";     
    } 
	
	/**
	 * 机构批复文件上传
	 * @param request
	 * @param multipartFile
	 * @return
	 */
	@PostMapping(value="/notice/attahment/upload")
	public ResultDto attahmentUpload(HttpServletRequest request,@RequestParam("file") MultipartFile multipartFile, String notId, String userId){
		// UserDto userDto = getCachedUserDto(request);
		// 判断大小是否满足条件
		if(multipartFile.getSize() > 52428800){
			return new ResultDto(ResultDto.TOKEN_FAIL,"文件过大",null);
		}
		UUID uuid = UUID.randomUUID();
		String filePath = attachmentPath + "/" + uuid;
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String fileUrl = filePath + suffix;
		File file = new File(fileUrl);
		if(!file.exists()){
			new File(fileUrl.substring(0, fileUrl.lastIndexOf("/"))).mkdirs();
		}
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Attachment attachment = new Attachment();
		attachment.setFileUrl(fileUrl);
		attachment.setCreateUserId(userId);
		attachment.setFileName(multipartFile.getOriginalFilename());
		attachment.setStatus("0");
		attachment.setNotId(notId);
		attachment.setCreateTime(new Date());
		return new ResultDto(ResultDto.CODE_SUCCESS,"", attachment);
	}
}
