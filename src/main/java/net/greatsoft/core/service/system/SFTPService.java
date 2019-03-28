package net.greatsoft.core.service.system;

import com.jcraft.jsch.*;
import net.greatsoft.core.util.UploadSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
public class SFTPService {

    private static Logger LOGGER = LoggerFactory.getLogger(SFTPService.class);

    private ChannelSftp client = null;

    private Session session = null;

    @Autowired
    private UploadSettings uploadSettings;

    private ChannelSftp makeConnection() {

        String privateKey = null;
        String username = uploadSettings.getUsername();
        String password = uploadSettings.getPassword();
        String ip = uploadSettings.getIp();
        int port = uploadSettings.getPort();
        if (client == null|| session == null || !client.isConnected() || !session.isConnected()) {
            try {
                JSch jsch = new JSch();
                if (privateKey != null) {
                    jsch.addIdentity(privateKey); // 设置私钥    
                }
                session = jsch.getSession(username, ip, port);
                if (password != null) {
                    session.setPassword(password);
                }
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.connect();
                Channel channel = session.openChannel("sftp");
                channel.connect();
                client = (ChannelSftp) channel;
                LOGGER.info("sftp服务器连接成功");
            } catch (JSchException e) {
                LOGGER.error("sftp登录失败，检测登录ip，端口号，用户名密码是否正确，错误信息为" + e.getMessage());
            }
        }
        return client;
    }


    @Transactional
    public boolean delete(String directory, String deleteFile){
    	boolean result = false;
        ChannelSftp sftp = makeConnection();
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (SftpException e) {
            LOGGER.error("sftp文件删除失败" + e.getMessage());
            e.printStackTrace();
            return result;
        } finally {
            close();
        }
        result = true;
        return result;
    }


    public boolean download(String directory, String downloadFile, String saveFile) {
        boolean result = false;
        Integer i = 0;
        while (!result) {
            ChannelSftp sftp = makeConnection();
            if (directory != null && !"".equals(directory)) {
                try {
                    sftp.cd(directory);
                } catch (SftpException e) {
                    LOGGER.error("sftp文件下载，目录不存在，错误信息" + e.getMessage());
                }
            }
            File file = new File(saveFile + downloadFile);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e1) {
                LOGGER.error("sftp文件下载失败，本地目录不存在" + e1.getMessage());
            }
            try {
                sftp.get(downloadFile, fileOutputStream);
                if (i > 0) {
                    LOGGER.info("sftp文件重试下载成功,sftp地址:"+directory+",本地文件地址:"+saveFile);
                } else {
                    LOGGER.info("sftp文件下载成功,sftp地址:"+directory+",本地文件地址:"+saveFile);
                }
                result = true;
            } catch (SftpException  e1) {
                i++;
                LOGGER.error("sftp文件下载失败，重试中。。。第"+i+"次，错误信息" + e1.getMessage());
                if(i > uploadSettings.getDownloadRettry()){
                    LOGGER.error("ftp文件下载失败，超过重试次数结束重试，错误信息" + e1.getMessage());
                    return result;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(uploadSettings.getDownloadSleep());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        close();
        return result;
    }

    public boolean upload(String basePath, String directory, InputStream inputStream, String fileName) {
        boolean result = false;
        Integer i = 0;
        while (!result) {
            ChannelSftp sftp = makeConnection();
            try {
                sftp.cd(basePath);
                sftp.cd(directory);
            } catch (SftpException e) {
                LOGGER.info("sftp文件上传，目录不存在开始创建");
                String [] dirs = directory.split("/");
                String tempPath = basePath;
                for(String dir : dirs){
                    if(null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    try{
                    	tempPath = tempPath.replaceAll(" ", "");
                        sftp.cd(tempPath);
                    }catch(SftpException ex){
                        try {
                            sftp.mkdir(tempPath);
                            sftp.cd(tempPath);
                        } catch (SftpException e1) {
                            LOGGER.error("sftp文件上传，目录创建失败，错误信息:"+e1.getMessage()+ex.getMessage());
                        }
                    }
                }
            }
            try {
                // File file = new File(filePath);
                sftp.put(inputStream , fileName);
                if(i > 0){
                    LOGGER.info("sftp重试文件上传成功，ftp路径:"+basePath+directory+",文件名称:" + fileName);
                }else{
                    LOGGER.info("sftp文件上传成功，ftp路径为"+basePath+directory+",文件名称:" + fileName);
                }
                result = true;
            } catch (Exception e) {
                i++;
                LOGGER.error("sftp文件上传失败，重试中。。。第"+i+"次，错误信息"+e.getMessage());
                if(i > uploadSettings.getUploadRettry()){
                    LOGGER.error("sftp文件上传失败，超过重试次数结束重试，错误信息"+e.getMessage());
                    return result;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(uploadSettings.getUploadSleep());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        close();
        return result;
    }

    private void close() {
        if (client != null) {
            if (client.isConnected()) {
                client.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
