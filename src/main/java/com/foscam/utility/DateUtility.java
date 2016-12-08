package com.foscam.utility;

import java.text.SimpleDateFormat;

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
public class DateUtility {

	public SimpleDateFormat getSimpleDateFormat(String dateFormatString) {
		return new SimpleDateFormat(dateFormatString);
	}
}
