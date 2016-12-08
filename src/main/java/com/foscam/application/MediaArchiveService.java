package com.foscam.application;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foscam.contracts.MediaArchiveServiceResponseTemplate;

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

@RestController
public class MediaArchiveService {

	private final AtomicLong counter = new AtomicLong();
	private static final String template = "Media Archive Request [%s]";
    
    @RequestMapping("/archiveMedia")
    public MediaArchiveServiceResponseTemplate archiveMedia(@RequestParam(value="sourcePath", required = true) String sourcePath, 
    		@RequestParam(value="targetPath", required = true) String targetPath,
    		@RequestParam(value="isVideoMedia", required = true) boolean isVideoMedia) throws IOException, ParseException {
       
    	MediaArchiveServiceController controller = new MediaArchiveServiceController();
    	
    return new MediaArchiveServiceResponseTemplate(counter.incrementAndGet(), 
    		   String.format(template, controller.archiveMedia(sourcePath, targetPath, isVideoMedia)));
    }
}
