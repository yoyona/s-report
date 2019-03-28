package net.greatsoft.core.service.system;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.greatsoft.core.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.greatsoft.core.domain.mapper.AttachmentMapper;
import net.greatsoft.core.domain.model.task.Attachment;
import net.greatsoft.core.repository.task.AttachmentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository; 
	
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private TaskService taskService;

	@Autowired
	private SFTPService sftpService;

	@Transactional
	public Attachment save(Attachment attachment) {
		return this.attachmentRepository.saveAndFlush(attachment);
	}
	
	public List<Attachment> findByOrgId(String orgId) {
		return this.attachmentRepository.findByOrgId2(orgId);
	}
	
	public List<Attachment> findUploadFileAll() {
		return this.attachmentMapper.findUploadFileList();
	}
	
	@Transactional
	public void delete(Long id) {
		Attachment attachment = this.attachmentRepository.findById(id);
		if (attachment == null) {
			return;
		}
		attachmentRepository.delete(id);
		// 删除文件
		File f = new File(attachment.getFileUrl());
		if (f.exists()) {
			f.delete();
		}
	}
	
	public Attachment findByCondition(String orgId, String category) {
		return this.attachmentRepository.findByCondition2(orgId, category);
	}

	@Transactional
	public boolean deleteSftpFile(Long id, String uploadBaseDirectory) {
		Attachment attachment = this.attachmentRepository.findById(id);
		boolean delete = sftpService.delete(uploadBaseDirectory, attachment.getFileName());
		if (delete) {
			attachmentRepository.delete(id);
		}
		return delete;
	}

	public boolean downloadSftpFile(Long id, String uploadBaseDirectory, String local, HttpServletRequest request, HttpServletResponse response) {
		Attachment attachment = this.attachmentRepository.findById(id);
		boolean download = sftpService.download(uploadBaseDirectory, attachment.getFileName(), local);
		if (download) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		taskService.download(response, local + attachment.getFileName(), attachment.getFileName());
		return download;
	}
}
