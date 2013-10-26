package com.gosociety.server.common.exception;

import org.apache.log4j.Logger;

public class GoSoParseException extends Exception {

	private static final Logger logger = Logger.getLogger(GoSoParseException.class);
			
	public GoSoParseException(Exception e, String response) {
		
		logger.debug("Caught parse exception when trying to parse this:\n"+response, e);
	}
}
