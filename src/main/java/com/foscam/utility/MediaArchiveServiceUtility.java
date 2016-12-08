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
	
}
