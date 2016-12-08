package com.foscam.application;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foscam.utility.FileUtility;

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
public class MediaArchiveServiceController {

	private static final Logger log = LoggerFactory.getLogger(MediaArchiveServiceController.class);
	private static final FileUtility util = new FileUtility();

	public String archiveMedia(String sourcePath, String targetPath, boolean isVideoMedia) throws IOException, ParseException {
		
		File srcFolder = new File(sourcePath);
		File[] srcListOfFiles = srcFolder.listFiles();
		File tgtFolder = new File(targetPath);
	    
	    log.info("Source Path Directory is [" + srcFolder.getPath() + "]");
	    log.info("Target Path Directory is [" + tgtFolder.getPath() + "]");
	    log.info("# of identified files on source folder are [" + srcListOfFiles.length + "]");
	    
	    String returnObjBuilder = "";
	    
	    returnObjBuilder = "Source Path Directory is '" + srcFolder.getPath() + "' - " 
	    					+ "Target Path Directory is '" + tgtFolder.getPath() + "' -"
	    					+ "# of identified files on source folder are '" + srcListOfFiles.length + "' ";
	    
	    util.archiveFiles(sourcePath, srcListOfFiles, targetPath, isVideoMedia, true);
		
		return returnObjBuilder;
		
	}
}
