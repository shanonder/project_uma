package app.uma.utils;

import java.io.File;

public class FileUtil {

	public static void deleteAll(File file){  

		if(file.isFile() || file.list().length ==0)  
		{  
			file.delete();       
		}else{      
			File[] files = file.listFiles();  
			for (int i = 0; i < files.length; i++) {  
				deleteAll(files[i]);  
				files[i].delete();      
			}  


			if(file.exists())         //如果文件本身就是目录 ，就要删除目录  
			file.delete();  
		}  
	}  

	public static void main(String[] args){       
		File f = new File("f:"+File.separator+"test");  
		deleteAll(f);  
	}  

	//  // 删除完文件后删除文件夹  
	//  // param folderPath 文件夹完整绝对路径  
	public static void delFolder(String folderPath) {  
		try {  
			delAllFile(folderPath); // 删除完里面所有内容  
			//不想删除文佳夹隐藏下面  
//			String filePath = folderPath;  
//			filePath = filePath.toString();  
//			java.io.File myFilePath = new java.io.File(filePath);  
//			myFilePath.delete(); // 删除空文件夹  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  

	// 删除指定文件夹下所有文件  
	// param path 文件夹完整绝对路径  
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
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件  
				//		              delFolder(path + "/" + tempList[i]);// 再删除空文件夹  
				flag = true;  
			}  
		}  
		return flag;  
	}  
}
