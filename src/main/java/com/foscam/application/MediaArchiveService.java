package com.foscam.application;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foscam.utility.MediaArchiveServiceUtility;

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

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/archiveVideos")
    public MediaArchiveServiceController greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new MediaArchiveServiceController(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/archivePhotos")
    public MediaArchiveServiceController printName(@RequestParam(value="sourcePath", required = true) String sourcePath, 
    		@RequestParam(value="targetPath", required = true) String targetPath,
    		@RequestParam(value="isArchiveMediaVideos", required = true) boolean isArchiveMediaVideos) throws IOException, ParseException {
       MediaArchiveServiceUtility util = new MediaArchiveServiceUtility();
    ;
       return new MediaArchiveServiceController(counter.incrementAndGet(),
                            String.format(template, util.archiveMedia(sourcePath, targetPath, isArchiveMediaVideos)));
    }
   
}
