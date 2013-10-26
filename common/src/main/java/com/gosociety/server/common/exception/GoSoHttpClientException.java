package com.gosociety.server.common.exception;

import java.net.URI;

import org.apache.log4j.Logger;

public class GoSoHttpClientException extends Exception {

	private static final Logger logger = Logger.getLogger(GoSoHttpClientException.class);
	
	public GoSoHttpClientException(Exception e, URI uri) {
		
		logger.debug("Caught http client exception when trying to reach this URI:\n"+uri.toString(), e);
	}
}
