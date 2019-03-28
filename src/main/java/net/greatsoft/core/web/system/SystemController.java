package net.greatsoft.core.web.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.greatsoft.core.domain.mapper.DicMapper;
import net.greatsoft.core.domain.model.system.Dic;
import net.greatsoft.core.domain.model.system.DicArea;
import net.greatsoft.core.domain.model.system.Message;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.Role;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.dto.system.UserDto;
import net.greatsoft.core.service.system.SystemService;
import net.greatsoft.core.service.system.UserService;
import net.greatsoft.core.web.BaseController;
import net.greatsoft.core.web.dto.ResultDto;
/**
 * 系统基础数据相关处理
 * @author fxy
 *
 */
@RestController
@RequestMapping("/system")
public class SystemController extends BaseController{
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DicMapper dicMapper;
	
	/**
	 * 查询行政区划
	 * @param areaId
	 * @return
	 */
	@GetMapping(value="/area/{areaId}")
	public ResultDto queryAreas(@PathVariable String areaId){
		Map<String,Object> result = new HashMap<String,Object>();
		List<DicArea> areas = systemService.queryAreas(areaId);
		result.put("areas", areas);
		// 通过areas来获取对应的机构列表
		List<Org> orgList = this.systemService.getOrgListByArea(areas.get(0));
		result.put("orgList", orgList);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询字典表
	 * @param type
	 * @return
	 */
	@GetMapping(value="/dic/{type}")
	public ResultDto queryDicsByType(@PathVariable String type){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Dic> dics = systemService.queryDics(type);
		result.put("dics", dics);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	/**
	 * 查询字典表(没有上级,只显示最下级的字典表信息,并且按顺序排序)
	 * @param type
	 * @return
	 */
	@GetMapping(value="/dic/withouthl/{type}")
	public ResultDto queryDicsByTypeWithouthl(@PathVariable String type){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Dic> dics = systemService.queryDicsWithouthl(type);
		result.put("dics", dics);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@GetMapping(value="/dic/tree/{type}")
	public ResultDto findDictsTree(@PathVariable String type){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Dic> dics = systemService.findDictsTree(type);
		result.put("dics", dics);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/message/list/{orgId}")
	public ResultDto findMessageList(@PathVariable String orgId,HttpServletRequest request){
		UserDto currentUser = getCachedUserDto(request);
		User user = userService.findUsersByUserId(currentUser.getId());
		Set<Role> roles = user.getRoles();
		boolean isAdmin = false;
		for (Role role: roles) {  
		      if("1".equals(role.getId())){
		    	  isAdmin = true;
		      }
		} 
		Map<String,Object> result = new HashMap<String,Object>();
		List<Message> msgs = null;
		//若为系统管理员，则查询所有消息
		if(isAdmin){
			msgs = systemService.findAllMessageList();
		}else{
			msgs = systemService.findMessageList(orgId);
		}
		result.put("msgs", msgs);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@Transactional
	@RequestMapping(value = "/message/add", method = RequestMethod.POST)
	public ResultDto add(@RequestBody Message msg, HttpSession session,HttpServletRequest request) {
		UserDto currentUser = getCachedUserDto(request);
		msg.setCreateUserId(currentUser.getId());
		msg.setCreateOrgId(currentUser.getAdminOrganizationId());
		msg.setCreateTime(new Date());
		systemService.addMessage(msg);
		return success("success");
	}

	/**
	 * 编辑用户信息
	 */
	@Transactional
	@RequestMapping(value = "/message/update", method = RequestMethod.POST)
	public ResultDto update(@RequestBody Message msg,
			HttpSession session,HttpServletRequest request) {
		UserDto currentUser = getCachedUserDto(request);
		msg.setAnswerUserId(currentUser.getId());
		Message message = systemService.updateMessage(msg);

		return success(message);
	}
	
	@RequestMapping(value = "/message/delete", method = RequestMethod.POST)
	public ResultDto delete(@RequestBody String id,
			HttpSession session,HttpServletRequest request) {
		systemService.deleteMessage(id);

		return success(null);
	}
}
