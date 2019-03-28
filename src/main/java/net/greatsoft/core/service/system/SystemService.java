package net.greatsoft.core.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.mapper.DicMapper;
import net.greatsoft.core.domain.mapper.OrgMapper;
import net.greatsoft.core.domain.model.system.Dic;
import net.greatsoft.core.domain.model.system.DicArea;
import net.greatsoft.core.domain.model.system.Message;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.repository.redis.CacheRedisDao;
import net.greatsoft.core.repository.system.DicAreaRepository;
import net.greatsoft.core.repository.system.DicRepository;
import net.greatsoft.core.repository.system.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {
	@Autowired
	private DicAreaRepository dicAreaRepository;
	@Autowired
	private DicRepository dicRepository;
	@Autowired
	private CacheRedisDao cacheRedisDao;
	@Autowired
	private OrgMapper orgMapper;
	@Autowired
	private DicMapper dicMapper;
	@Autowired
	private MessageRepository messageRepository;
	/**
	 * 查询行政区划
	 * @param areaId
	 * @return
	 */
	public List<DicArea> queryAreas(String areaId){
		@SuppressWarnings("unchecked")
		List<DicArea> areas = (List<DicArea>)cacheRedisDao.get("areaList");
		List<DicArea> returnAreas = new ArrayList<DicArea>();
		for (int i = 0; i < areas.size(); i++) {
			DicArea area = areas.get(i);
			if(areaId == null || "0".equals(areaId)){
				if(area.getpId() == null || "".equals(area.getpId())){
					returnAreas.add(area);
				}
			}else{
				if(areaId.equals(area.getpId())){
					returnAreas.add(area);
				}
			}
		}
		/*if(areaId == null || areaId.equals("0")){
			areas = dicAreaRepository.queryTopAreas();
		}else{
			areas = dicAreaRepository.queryAreasByPid(areaId);
		}*/
		return returnAreas;
	}
	/**
	 * 增加逻辑,方便登录选择机构进行机构全部检索
	 * @param area
	 * @return
	 */
	public List<Org> getOrgListByArea(DicArea area){
		List<Org> orgList = null;
		if (!area.getId().endsWith("0000")) {
			if (area.getpId().endsWith("0000")) {
				// orgList = this.orgMapper.getOrgsByProvinces(area.getpId().substring(0, 2));
				orgList = (List<Org>) cacheRedisDao.get(area.getpId().substring(0, 2)+"%");
			} else {
				/*orgList = this.orgMapper.getOrgsByProvinces(area.getpId().substring(0, 4));*/
				orgList =  (List<Org>) cacheRedisDao.get(area.getpId().substring(0, 4)+"_");
			}
		}
		return orgList;
	}
	/**
	 * 根据类型查询字典表数据
	 * @param type
	 * @return
	 */
	public List<Dic> queryDics(String type){
		List<Dic> areas = dicRepository.queryDicsByType(type);
		return areas;
	}
	/**
	 * 根据类型查询字典表数据(只查询最下级的字典表信息)
	 * @param type
	 * @return
	 */
	public List<Dic> queryDicsWithouthl(String type) {
		List<Dic> areas = dicRepository.queryDicsWithouthl(type);
		return areas;
	}
	/**
	 * 根据类型查询树形列表的字典表信息
	 * @param type
	 * @return
	 */
	public List<Dic> findDictsTree(String type) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("type",type);
		return this.dicMapper.findDictByCondition(param);
	}
	
	public List<Message> findAllMessageList() {
		List<Message> msgs = messageRepository.findAllMessageList();
		return msgs;
	}
	public void addMessage(Message msg) {
		/*if (messageRepository.findUserByName(Message.getName()) != null) {
			throw new DomainRuntimeException("用户名已存在");
		}*/
		
		messageRepository.save(msg);
	}

	/**
	 * 编辑用户，同时编辑用户与角色关系
	 */
	public Message updateMessage(Message msg) {
		Message message = messageRepository.findOne(msg.getId());
		if(message == null){
			return null;
		}
		message.setAnswerContext(msg.getAnswerContext());
		message.setAnswerTime(new Date());
		message.setAnswerUserId(msg.getAnswerUserId());
		messageRepository.save(message);
		return msg;
	}
	
	public void deleteMessage(String id) {
		Message message = messageRepository.findMessageById(id);
		messageRepository.delete(message);
	}
	
	public List<Message> findMessageList(String orgId) {
		List<Message> msgs = messageRepository.findMessageList(orgId);
		return msgs;
	}
}
