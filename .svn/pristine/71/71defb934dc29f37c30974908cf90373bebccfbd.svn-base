package net.greatsoft.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * 操作文件的工具类
 * 
 * @author litian
 *
 */
public class FileUtils {
	/**
	 * 拷贝整个文件夹以及其下的所有的文件
	 * @param originalPath
	 * @param targetPath
	 */
	public static void copyFiles(String originalPath, String targetPath) {
		try {
			File file = new File(originalPath);
			if (file.isDirectory()) {
				File f = new File(targetPath);
				if (!f.exists())
					f.mkdir();
				File[] files = file.listFiles();
				for (File file2 : files) {
					copyFiles(file2.toString(), targetPath + "/" + file2.getName());
				}
			} else {
				File f = new File(targetPath);
				if(!f.exists()){
					new File(targetPath.substring(0,targetPath.lastIndexOf("\\"))).mkdir();
				}
				DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(originalPath)));
				byte[] date = new byte[in.available()];
				in.read(date);
				DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(targetPath)));
				out.write(date);
				in.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//删除文件夹  
	public static void delFolder(String folderPath) {  
	     try {  
	        delAllFile(folderPath); //删除完里面所有内容  
	        String filePath = folderPath;  
	        filePath = filePath.toString();  
	        java.io.File myFilePath = new java.io.File(filePath);  
	        myFilePath.delete(); //删除空文件夹  
	     } catch (Exception e) {  
	       e.printStackTrace();   
	     }  
	} 
	/**
	 * 删除文件夹下面的所有文件和文件夹但是保留当前文件夹
	 * @param path
	 */
	public static boolean delAllFile(String path) {
	       boolean flag = false;  
	       File file = new File(path);  
	       if (!file.exists()) {  
	         return flag;  
	       }  
	       if (!file.isDirectory()) {
	         return flag;  
	       }  
	       String[] tempList = file.list();  
	       File temp = null;  
	       for (int i = 0; i < tempList.length; i++) {  
	          if (path.endsWith(File.separator)) {  
	             temp = new File(path + tempList[i]);  
	          } else {  
	              temp = new File(path + File.separator + tempList[i]);  
	          }  
	          if (temp.isFile()) {  
	             temp.delete();  
	          }  
	          if (temp.isDirectory()) {  
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件  
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹  
	             flag = true;  
	          }  
	       }  
	       return flag;  
	     }  
	
	public static void main(String[] args) {
		String t = "D:\\file\\temp";
		String p = "D:\\2\\.m2";
		if(!new File(p).exists()){
			new File(p).mkdirs();
		}
	}
}
