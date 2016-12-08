package com.foscam.contracts;

public class MediaArchiveServiceResponseTemplate {

	private final long id;
    private final String content;

    public MediaArchiveServiceResponseTemplate(long id, String content) {
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
