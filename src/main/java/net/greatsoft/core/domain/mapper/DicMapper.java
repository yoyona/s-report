package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.model.system.Dic;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DicMapper {
	/**
	 * 查询字典表
	 * @return
	 */
	List<Dic> findDics();
	
	/**
	 * 更新字典表信息
	 * @param dic
	 */
	void updateDic(Dic dic);
	
	/**
	 * 保存字典对象
	 * @param dic
	 */
	void insertDic(Dic dic);
	
	/**
	 * 删除字典对象
	 * @param dic
	 */
	void deleteDic(Dic dic);
	
	/**
	 * 根据条件查询字典表信息 有排序
	 * @param param
	 * @return
	 */
	List<Dic> findDictByCondition(Map<String,Object> param);
}
