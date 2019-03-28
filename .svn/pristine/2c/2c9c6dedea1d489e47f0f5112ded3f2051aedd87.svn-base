package net.greatsoft.core.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.greatsoft.core.domain.model.task.FileAdditional;

@Mapper
public interface FileAdditionalMapper {
	/**
	 * 通过任务Id删除附件信息
	 * @param taskId
	 */
	public void deleteFileAdditionalByTaskId(String taskId);
	/**
	 * 查询机构信息里面的上传文件列表
	 * @param param
	 * @return
	 */
	public List<FileAdditional> getUploadFileList(Map<String,Object> param);
}
