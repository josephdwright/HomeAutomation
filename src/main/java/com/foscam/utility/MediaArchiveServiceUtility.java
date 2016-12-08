package com.foscam.utility;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

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
public class MediaArchiveServiceUtility {

	private static final Logger log = LoggerFactory.getLogger(MediaArchiveServiceUtility.class);
	
	public String archiveMedia(String sourcePath, String targetPath, boolean isVideoMedia) throws IOException, ParseException {
		
		File srcFolder = new File(sourcePath);
		File[] srcListOfFiles = srcFolder.listFiles();
		File tgtFolder = new File(targetPath);
	    
	    log.info("Source Path Directory is [" + srcFolder.getPath() + "]");
	    log.info("Target Path Directory is [" + tgtFolder.getPath() + "]");
	    log.info("# of identified files on source folder are [" + srcListOfFiles.length + "]");
	    int fileCounter = 0;
	    
	    String returnObjBuilder = "";
	    
	    for(File file : srcListOfFiles) {
	    	fileCounter++;
	    	log.info("File # [" + fileCounter + "] " + file.getName());
	    	String getDateString = file.getName().substring(8,16);
	    	String getYearString = getDateString.substring(0, 4);
	    	String getMonthString = getDateString.substring(4,6);
	    	String getDayString = getDateString.substring(6,8);
	    	returnObjBuilder = returnObjBuilder + getYearString + "-" + getMonthString + "-" + getDayString + "; ";	
	    }
	    
	    archiveFiles(sourcePath, srcListOfFiles, targetPath, isVideoMedia, true);
		
		return returnObjBuilder;
		
	}
	
	public boolean archiveFiles(String sourcePath, File[] filesToArchive, String targetPath, boolean isVideoMedia, boolean removeAfterArchiving) throws IOException, ParseException {
		
		SimpleDateFormat sdf_IncomingDate_MM_DD_YYYY = isVideoMedia ? new SimpleDateFormat("yyyyMMdd_HHmmss") : new SimpleDateFormat("yyyyMMdd-HHmmss");
		SimpleDateFormat sdf_OutgoingDateFolderDirectoryYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd/HH");

		String srcFileName = "";
		String dateSubFolderStructure = "";

		String finalSourcePathNameStr = "";
		String finalTargetPathNameStr = "";
	
		for(File file : filesToArchive) {
			srcFileName = file.getName();
			
			if(srcFileName.length() < 23) {
				log.info("Skipping archive of [" +srcFileName + "]");
			} else {
				
				Date asOfDateParsed = sdf_IncomingDate_MM_DD_YYYY.parse(file.getName().substring(8,23));
				dateSubFolderStructure = sdf_OutgoingDateFolderDirectoryYYYYMMDD.format(asOfDateParsed);
				finalSourcePathNameStr = sourcePath + srcFileName;
				finalTargetPathNameStr = targetPath + dateSubFolderStructure + "/" + srcFileName;
				
				log.info("Building Source Path [" + finalSourcePathNameStr + "]");
				log.info("Building Target Path [" + finalTargetPathNameStr + "]");
				
				File finalSourcePathName =  new File(finalSourcePathNameStr);
				File finalTargetPathName =  new File(finalTargetPathNameStr);
				File finalTargetPathNameDir =  new File(finalTargetPathNameStr.substring(0, finalTargetPathNameStr.lastIndexOf("/")));
				
				finalTargetPathNameDir.mkdirs();
				
				FileCopyUtils.copy(finalSourcePathName, finalTargetPathName);

				if(removeAfterArchiving) {
					finalSourcePathName.delete();	
				}
			}

		}

		return true;
	}
	
	public boolean compressArchiveMedia() {
		return false;
	}
	
}
