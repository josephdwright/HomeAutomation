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
public class FileUtility {

	private static final Logger log = LoggerFactory.getLogger(FileUtility.class);
	private static final DateUtility dateUtility = new DateUtility();
		
	public boolean archiveFiles(String sourcePathStr, File[] filesToArchive, String targetPathStr, boolean isVideoMedia, boolean removeAfterArchiving) throws IOException, ParseException {
		
		SimpleDateFormat sdf_IncomingDate_MM_DD_YYYY = isVideoMedia ? getSimpleDateFormat("yyyyMMdd_HHmmss") : getSimpleDateFormat("yyyyMMdd-HHmmss");
		SimpleDateFormat sdf_OutgoingDateFolderDirectoryYYYYMMDD = getSimpleDateFormat("yyyy/MM/dd/HH");

		String srcFileName = "";
		String dateSubFolderStructure = "";

		String sourcePathNameStr = "";
		String targetPathNameStr = "";
	
		for(File file : filesToArchive) {
			srcFileName = file.getName();
			
			if(srcFileName.length() < 23) {
				log.info("Skipping archive of [" +srcFileName + "]");
			} else {
				
				Date asOfDateParsed = sdf_IncomingDate_MM_DD_YYYY.parse(file.getName().substring(8,23));
				dateSubFolderStructure = sdf_OutgoingDateFolderDirectoryYYYYMMDD.format(asOfDateParsed);
				sourcePathNameStr = sourcePathStr + srcFileName;
				targetPathNameStr = targetPathStr + dateSubFolderStructure + "/" + srcFileName;
				
				log.info("Building Source Path [" + sourcePathNameStr + "]");
				log.info("Building Target Path [" + targetPathNameStr + "]");
				
				File sourcePathName =  new File(sourcePathNameStr);
				File targetPathName =  new File(targetPathNameStr);
				File targetPathNameDir =  new File(targetPathNameStr.substring(0, targetPathNameStr.lastIndexOf("/")));
				
				checkDirectoryStructure(targetPathNameDir);
				copyFile(sourcePathName, targetPathName);

				if(removeAfterArchiving) {
					deleteFile(sourcePathName);	
				}
			}

		}

		return true;
	}
	
	private SimpleDateFormat getSimpleDateFormat(String simpleDateFormatStr) {
		return dateUtility.getSimpleDateFormat(simpleDateFormatStr);
	}
	
	private void checkDirectoryStructure(File directoryStructure) {
		if(directoryStructure.mkdirs()) {
			log.info("created directory path [" + directoryStructure.getPath() + "]");
		}
	}
	
	private void copyFile(File sourcePath, File targetPath) throws IOException {
		FileCopyUtils.copy(sourcePath, targetPath);
	}
	
	private void deleteFile(File sourcePath) {
		sourcePath.delete();
	}
	
}
