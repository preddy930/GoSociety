package com.gosociety.server.services.json;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseJsonResponse {
	
	public static final int COULD_NOT_CREATE_USER = 10001;
	public static final int USER_EXISTS = 10002;
	public static final int INVALID_CREDS = 10003;
	public static final int COULD_NOT_REFRESH_SOCIETY_DATA = 10004;
	public static final int BING_SERVICES_UNREACHABLE = 10005;
	public static final int UNABLE_TO_PARSE_REVERSE_GEOCODE_BING = 10006;
	public static final int FACEBOOK_ID_EXISTS_IN_SYSTEM = 10007;
	public static final int SEARCH_EXPIRED = 10008;
	public static final int COULD_NOT_UPDATE_FBTOKEN = 10009;
	
	private String errorString;
	private int errorCode;
	
	private static final Map<Integer, String> errorMap;
	
	
	static {
        Map<Integer, String> aMap = new HashMap <Integer, String>();
        
        aMap.put(COULD_NOT_CREATE_USER,"Could not complete user creation request due to invalid sign up credentials");
        aMap.put(USER_EXISTS,"This e-mail address already exists in the database");
        aMap.put(INVALID_CREDS,"Login credentials are invalid");
        aMap.put(COULD_NOT_REFRESH_SOCIETY_DATA, "The user does not exist in the system");
        aMap.put(BING_SERVICES_UNREACHABLE, "HTTP requests to Bing REST services are timing out or not being answered");
        aMap.put(UNABLE_TO_PARSE_REVERSE_GEOCODE_BING, "Response data for reverse geocoding is invalid (Bing)");
        
        //{0} represents the email address in the system associated to that facebook id
        aMap.put(FACEBOOK_ID_EXISTS_IN_SYSTEM, "{0}");
        
        aMap.put(SEARCH_EXPIRED, "Please perform search again, previous search has expired");
        aMap.put(COULD_NOT_UPDATE_FBTOKEN,"Error updating facebook token for the following reason: {0}");
        
        errorMap = Collections.unmodifiableMap(aMap);
    }

	public BaseJsonResponse() {
		errorString = "";
	}
	
	public void setError(int errorCode) {
		this.errorCode = errorCode;
		this.errorString = errorMap.get(errorCode);
	}

	public String getErrorString() {
		return errorString;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setError(int errorCode, Object [] values) {
		
		String preFormatted = errorMap.get(errorCode);
		
		MessageFormat form = new MessageFormat(preFormatted);
		preFormatted = form.format(values);
		
		this.errorCode = errorCode;
		this.errorString = preFormatted;
	}
}
