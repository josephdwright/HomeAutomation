package com.foscam.contracts;
/**
 * 
 * @author Wright
 * @version 1.0 December 3rd, 2016
 * <p>
 * 
 * <pre>
 * ===================================================================*
 * Copyright Notice
 * This file contains proprietary information of Joseph D Wright.
 * Copying or reproduction without prior writting approval is prohibited.
 * Copyright (c) 2016
 * </pre>
 * 
 * </p>
 */
public class FileMappingContract {

	private String sourcePath = null; // "c:/users/joseph/pictures/foscamops/"
	private String targetPath = null; // "z:/foscamarchive/"
		
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	
	@Override
	public String toString() {
		return "FileMappingContract [sourcePath=" + sourcePath + ", targetPath=" + targetPath + "]";
	}

}
