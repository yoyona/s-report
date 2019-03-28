package net.greatsoft.core.web.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.greatsoft.core.service.system.SFTPService;
import net.greatsoft.core.util.DateUtil;
import net.greatsoft.core.util.UploadSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.greatsoft.core.domain.model.task.Attachment;
import net.greatsoft.core.service.system.AttachmentService;
import net.greatsoft.core.web.dto.ResultDto;

/**
 * 上传文件控制器
 * @author Aptx4869
 *
 */
@RestController
@RequestMapping(value = "/uploadFile")
public class UploadController {

	@Value("${sftp.uploadBaseDirectory}")
	private String uploadBaseDirectory;

	@Value("${sftp.uploadBasePath}")
	private String uploadBasePath;

	@Value("${sftp.local}")
	private String local;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private UploadSettings uploadSettings;

	@Autowired
	private SFTPService sftpService;

	/**
	 * 上传文件
	 * @param multipartFile
	 * @param userId
	 * @param category
	 * @param orgId
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/attachment/upload")
	@Transactional
	public ResultDto attahmentFileUpload(@RequestParam("file") MultipartFile multipartFile, String userId, String category, String orgId, HttpServletRequest request) throws IOException {
		String uploadBaseDirectory = uploadSettings.getUploadBaseDirectory();
		String prefix = "";
		if ("0".equals(category)) {
			prefix = "分析报告_";
		} else if ("1".equals(category)) {
			prefix = "编制说明_";
		} else if ("2".equals(category)) {
			prefix = "其他_";
		}
		if ("0".equals(category)  || "1".equals(category)) {
			Attachment findByCondition = this.attachmentService.findByCondition(orgId, category);
			if (findByCondition != null) {
				this.attachmentService.deleteSftpFile(findByCondition.getId(), uploadBaseDirectory);
			}
		}
		String fileName = multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().indexOf("."));
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		fileName = prefix + fileName + "_" + DateUtil.format(new Date(), "yyyy-MM-dd HH_mm_ss") + suffix;
		String fileUrl = uploadSettings.getUploadBasePath() + uploadBaseDirectory + "/" + fileName;
		InputStream inputStream = multipartFile.getInputStream();
		boolean attachment1 = sftpService.upload(uploadSettings.getUploadBasePath(), uploadBaseDirectory, inputStream, fileName);
		Attachment attachment = new Attachment();
		attachment.setFileUrl(fileUrl);
		attachment.setCreateUserId(userId);
		attachment.setFileName(fileName);
		attachment.setStatus("0");
		attachment.setCreateTime(new Date());
		attachment.setCategory(category);
		attachment.setOrgId(orgId);
		attachment.setType(suffix);
		Attachment saveAndFlush = this.attachmentService.save(attachment);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", saveAndFlush);
	}

	/**
	 * 根据机构Id查询上传文件
	 * @param orgId
	 * @return
	 */
	@GetMapping(value = "/attachment/findByOrgId/{orgId}")
	public List<Attachment> findByOrgId(@PathVariable String orgId) {
		return this.attachmentService.findByOrgId(orgId);
	}

	/**
	 * 查询所有上传文件
	 * @return
	 */
	@GetMapping(value = "/attachment/findUploadFileAll")
	public List<Attachment> findUploadFileAll() {
		return this.attachmentService.findUploadFileAll();
	}

	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/attachment/delete/{id}")
	public ResultDto deleteUploadFile(@PathVariable Long id) {
		Map<String,Object> result = new HashMap<String,Object>();
		// 删除远端服务器的文件
		boolean b = this.attachmentService.deleteSftpFile(id, uploadBaseDirectory);
		result.put("flag", b);
		return new ResultDto(ResultDto.CODE_SUCCESS,"", result);
	}
}
