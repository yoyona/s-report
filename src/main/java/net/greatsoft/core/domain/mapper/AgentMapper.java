/*
 * 版权所有 2018 冠新软件。
 * 保留所有权利。
 */
package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 代理相关方法
 * @since 2018-12-13
 */
@Mapper
public interface AgentMapper {
	public List<Map<String,Object>> queryAgentUserList(Map<String, Object> map);
}
