package com.foscam.application;

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

    private final long id;
    private final String content;

    public MediaArchiveServiceController(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public void archiveMedia() {
    	
    }
    
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
