package net.greatsoft.core.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greatsoft.core.domain.mapper.DicMapper;
import net.greatsoft.core.domain.model.system.Dic;
import net.greatsoft.core.repository.system.DicRepository;

@Service
public class DicService {
	@Autowired
	private DicMapper dicMapper;
	@Autowired
	private DicRepository dicRepository;
	/**
	 * 查询字典表数据
	 * @return
	 */
	public List<Dic> findDics(){
		List<Dic> dics = dicMapper.findDics();
		return dics;
	}
	
	/**
	 * 更新字典对象
	 * @param dic
	 * @return
	 */
	public Dic updateDic(Dic dicParam){
		List<Dic> searchDic = dicRepository.queryDicsByIdAndType(dicParam.getId(), dicParam.getType());
		if(searchDic != null && searchDic.size() > 0){
			dicMapper.updateDic(dicParam);
		}else{
			dicMapper.insertDic(dicParam);
		}
		return null;
	}
	
	public void deleteDic(Dic dic){
		dicMapper.deleteDic(dic);
	}
}
