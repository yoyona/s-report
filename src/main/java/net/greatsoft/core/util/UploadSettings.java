package net.greatsoft.core.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * sftp.uploadBaseDirectory = /upload 
sftp.uploadBasePath = / 
sftp.uploadRettry= 10  
sftp.uploadSleep= 100 
sftp.downloadSleep= 100 
sftp.password= BHGX+guanxin2018
sftp.username= root
sftp.port= 22
sftp.ip= 106.37.74.236
sftp.local = D:\\file\\
 * @author Aptx4869
 *
 */
@ConfigurationProperties(prefix = "sftp")
@PropertySource(value = "classpath:/upload.properties")
@Component
public class UploadSettings {

	private String uploadBaseDirectory;
	
	private String uploadBasePath;
	
	private int uploadRettry;

	private int uploadSleep;
	
	private int downloadSleep;

	private int downloadRettry;
	
	private String username;
	
	private String password;
	
	private String ip;
	
	private int port;
	
	private String local;

	public String getUploadBaseDirectory() {
		return uploadBaseDirectory;
	}

	public void setUploadBaseDirectory(String uploadBaseDirectory) {
		this.uploadBaseDirectory = uploadBaseDirectory;
	}

	public String getUploadBasePath() {
		return uploadBasePath;
	}

	public void setUploadBasePath(String uploadBasePath) {
		this.uploadBasePath = uploadBasePath;
	}

	public int getUploadRettry() {
		return uploadRettry;
	}

	public void setUploadRettry(int uploadRettry) {
		this.uploadRettry = uploadRettry;
	}

	public int getUploadSleep() {
		return uploadSleep;
	}

	public void setUploadSleep(int uploadSleep) {
		this.uploadSleep = uploadSleep;
	}

	public int getDownloadSleep() {
		return downloadSleep;
	}

	public void setDownloadSleep(int downloadSleep) {
		this.downloadSleep = downloadSleep;
	}

	public int getDownloadRettry() {
		return downloadRettry;
	}

	public void setDownloadRettry(int downloadRettry) {
		this.downloadRettry = downloadRettry;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}
