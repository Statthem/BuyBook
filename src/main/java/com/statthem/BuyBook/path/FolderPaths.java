package com.statthem.BuyBook.path;

import java.io.File;

public enum FolderPaths {
	
	IMAGE_FOLDER("webapps" + File.separator +"BuyBook" + File.separator +"resources" + File.separator + "images"),
	BACKUP_IMAGE_FOLDER("images"),
	BOOK_FOLDER("webapps" + File.separator +"BuyBook" + File.separator +"resources" + File.separator +"books"),
	BACKUP_BOOK_FOLDER("books");
	
    private String path;
	
	private FolderPaths(){
		
	}
	
    private FolderPaths(String path){
    	String rootPath = System.getProperty("catalina.home");
		this.path = rootPath + File.separator + path;
	}
    
    public String getPath() {
    	return this.path;
    }

	
}
