package net.greatsoft.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.greatsoft.core.service.system.AttachmentService;
import net.greatsoft.core.util.UploadSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 下载文件
 * @author Aptx4869
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;

import net.greatsoft.core.domain.model.task.FileAdditional;
import net.greatsoft.core.repository.task.FileAdditionalRepository;
import net.greatsoft.core.service.task.TaskService;
@Controller
public class FileDownloadController {
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private UploadSettings uploadSettings;
	
	
	@GetMapping(value="/file/download/{fileId}")
	public void downFile(@PathVariable Long fileId,HttpServletRequest request,HttpServletResponse response){
		taskService.downloadFile(fileId, request, response);
	}
	
	@GetMapping(value="/attachment/download/{fileId}")
	public void downloadAttachment(@PathVariable Long fileId,HttpServletRequest request,HttpServletResponse response){
		taskService.downloadAttachment(fileId,request, response);
	}

	@GetMapping(value="/attachment/sftp/download/{fileId}")
	public void downloadSftpAttachment(@PathVariable Long fileId,HttpServletRequest request,HttpServletResponse response){
		// taskService.downloadAttachment(fileId,request, response);
		this.attachmentService.downloadSftpFile(fileId, uploadSettings.getUploadBaseDirectory(), uploadSettings.getLocal(), request, response);
	}
}
