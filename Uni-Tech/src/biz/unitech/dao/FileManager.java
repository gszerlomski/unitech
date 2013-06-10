package biz.unitech.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.nio.file.StandardCopyOption.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileManager {
	
	private String path = "/home/konrad/ordersTesty/";
	private String directory;
	
	public FileManager(String directory) {
		this.directory = directory;
	}

	public String getPath() {
		return path;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
    public void addFiles(List<CommonsMultipartFile> orderFiles) {

    	  try {
             
    		this.createDir();  
    		  
    	    for(MultipartFile file: orderFiles) {
			 
			   String fileName = null;
			   InputStream inputStream = null;
			   inputStream = file.getInputStream();
			   fileName = path + directory+ "/" + file.getOriginalFilename();
			   file.transferTo(new File(fileName));
			   inputStream.close(); 
		    }
    	    
          } catch(Exception e) {
        	  System.out.println("Unable to add files");
          }	
    }
    
	public void createDir() {
		File tmp = new File(path+directory);
		if(!(tmp.exists() && tmp.isDirectory())) {
        tmp.mkdir();
		}
	}
	
	public List<String> readDir() {
		File tmp = new File(path+directory);
		List<String>tmpArray = Arrays.asList(tmp.list());
		List<String> fileList = new ArrayList<String>();
		for(String name : tmpArray) {
			if(!name.equalsIgnoreCase("archive")) {
				fileList.add(name);
			}
		}
		return fileList;
	}
	
	public boolean dirExist() {
        File tmp = new File(path+directory);
        return tmp.exists();
	}
	
	public void deleteFile(String name) throws IOException {
		File tmp = new File(path+directory+"/archive");
		if(!(tmp.exists() && tmp.isDirectory())) {
	        tmp.mkdir();
		}
        Path source = Paths.get(path+directory+"/"+name);
        Path target = Paths.get(path+directory+"/archive/"+name);
		Files.copy(source, target,REPLACE_EXISTING);
        Files.delete(source);
	}

}   
