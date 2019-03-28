package net.greatsoft.core.web.notice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.greatsoft.core.domain.model.system.Notice;
import net.greatsoft.core.domain.model.task.Task;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.service.notice.NoticeService;
import net.greatsoft.core.util.constant.MessageConstant;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 通知公告相关功能
 * @author fxy
 *
 */
@RestController
@RequestMapping(value="/notice")
public class NoticeController extends BaseController{
	
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping(value="/findByCondition/{pageNo}/{pageSize}")
	public ResultDto findByCondition(@RequestBody Map<String,Object> param,@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		Page<Notice> page = PageHelper.startPage(pageNo, pageSize);
		List<Notice> noticeList = this.noticeService.findByCondition(param);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("noticeList", noticeList);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize",page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/save")
	public ResultDto saveNotice(@RequestBody Notice notice,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		UserDto user = getCachedUserDto(request);
		notice.setCreateTime(new Date());
		notice.setCreateUserId(user.getId());
		notice.setCategory(MessageConstant.ANNOUNCEMENT_CATEGORY);
		notice.setStatus(MessageConstant.VALID_STATUS);
		Notice n = this.noticeService.save(notice);
		result.put("notice", n);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	/**
	 * 新增消息
	 * @param notice
	 * @param request
	 * @return
	 */
	@PostMapping(value="/message/save")
	public ResultDto saveMessage(@RequestBody Map<String,Object> param,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		// 获取当前登录的用户
		UserDto userDto = getCachedUserDto(request);
		Notice notice = JSON.parseObject(JSON.toJSONString(param.get("notice")),Notice.class);
		List<String> userIds = (List<String>) param.get("userIds");
		boolean f = this.noticeService.sendMessage(null, notice.getOrgId(), notice.getContent(), userIds, userDto,notice.getTitle());
		result.put("flag", f);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/update")
	public ResultDto updateNotice(@RequestBody Notice notice){
		Map<String,Object> result = new HashMap<String,Object>();
		Notice n = this.noticeService.save(notice);
		result.put("notice", n);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	

	@GetMapping(value="/findById/{id}")
	public ResultDto findById(@PathVariable String id){
		Map<String,Object> result = new HashMap<String,Object>();
		Notice notice = this.noticeService.findById(id);
		result.put("notice",notice);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	} 
	
	@PostMapping(value="/delete")
	public ResultDto deleteById(@RequestBody Notice notice){
		Map<String,Object> result = new HashMap<String,Object>();
//		Boolean flag = this.noticeService.deleteById(id);
		notice.setStatus(MessageConstant.INVALID_STATUS);
		Notice save = this.noticeService.save(notice);
		result.put("notice", save);
		return new ResultDto(ResultDto.CODE_SUCCESS, "删除成功", result);
	}
	/**
	 * 通过当前登陆用户id获取消息列表
	 * @param param
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@PostMapping(value="/findNoticeByUserId/{pageNo}/{pageSize}")
	public ResultDto findNoticeByUserId(@RequestBody Map<String,Object> param,HttpServletRequest request,@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		Page<Notice> page = PageHelper.startPage(pageNo, pageSize);
		UserDto user = getCachedUserDto(request);
		List<Notice> noticeList = this.noticeService.findNoticeByUserId(user.getId());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("noticeList", noticeList);
		result.put("pageNo", page.getPageNum());
		result.put("pageSize",page.getPageSize());
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		result.put("pages", page.getPages());
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@GetMapping(value="/readMessage")
	public ResultDto readMessage(String notId,HttpServletRequest request){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("notId", notId);
		UserDto user = getCachedUserDto(request);
		param.put("userId", user.getId());
		Integer r = this.noticeService.updateNoticeUserRel(param);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("flag",r >= 1 ? true : false);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询通知公告页面数据
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/findByCondition")
	public ResultDto findByCondition(@RequestBody Map<String,Object> param, HttpServletRequest request){
		UserDto user = getCachedUserDto(request);
		param.put("userId", user.getId());
		List<Notice> noticeList = this.noticeService.findNoticeRecord(param);
		List<Notice> noticeListFile = this.noticeService.findNoticeRecordFile(param);
		noticeList.addAll(noticeListFile);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("noticeList", noticeList);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询通知公告页面所有数据(与用户关联)
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/record/list")
	public ResultDto findNoticeRecord(@RequestBody Map<String,Object> param, HttpServletRequest request){
		UserDto user = getCachedUserDto(request);
		param.put("userId", user.getId());
		List<Notice> noticeList = this.noticeService.findNoticeRecord(param);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("noticeList", noticeList);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询通知公告下载文件
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/record/file")
	public ResultDto findNoticeRecrodFile(@RequestBody Map<String,Object> param){
		List<Notice> noticeListFile = this.noticeService.findNoticeRecordFile(param);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("noticeList", noticeListFile);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/record/save")
	public ResultDto saveNoticeRecord(@RequestBody Notice notice,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		UserDto user = getCachedUserDto(request);
		notice.setCreateTime(new Date());
		notice.setCreateUserId(user.getId());
		// notice.setCategory(MessageConstant.ANNOUNCEMENT_CATEGORY);
		notice.setStatus(MessageConstant.VALID_STATUS);
		Notice n = this.noticeService.save(notice);
		if (MessageConstant.NOTICE_RECORD.equals(n.getCategory())) {
			this.noticeService.initNoticeReacordRel(n);
		}
		result.put("notice", n);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
}
