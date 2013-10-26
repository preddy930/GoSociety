package com.gosociety.server.services.external.bing;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.gosociety.server.common.exception.GoSoHttpClientException;
import com.gosociety.server.common.exception.GoSoParseException;
import com.gosociety.server.common.util.GoSoHttpClient;
import com.gosociety.server.services.json.GoSoJsonParser;
import com.gosociety.server.services.model.GoSoBingReverseGeoCodeResponse;

public class BingReverseGeoCodeResolver{

	//this is being set in the spring configuration
	private GoSoJsonParser parser;
	private GoSoHttpClient client;
	
	private Map<String,String> params = new HashMap<String,String>() {
		{
			put("key", BingConstants.bingkey);
		}
	};

	public void setClient(GoSoHttpClient client) {
		this.client = client;
	}
	
	public GoSoBingReverseGeoCodeResponse resolveReverseGeoCode(String coords) throws GoSoParseException, GoSoHttpClientException {
		
		Object response=null;
		String bingResponse = null;
		try {
			bingResponse = client.getRequest(BingConstants.bingdomain, BingConstants.locationsPath+"/"+coords, params, true);
			
			if (bingResponse!=null || bingResponse.length()>0) {
				response = parser.parse(bingResponse);
			}
		} catch (JsonParseException e) {
			throw new GoSoParseException(e,bingResponse);
		} catch (JsonMappingException e) {
			throw new GoSoParseException(e,bingResponse);
		} catch (IOException e) {
			throw new GoSoParseException(e,bingResponse);
		}
		
		return (GoSoBingReverseGeoCodeResponse)response;
	}

	public GoSoJsonParser getParser() {
		return parser;
	}

	public void setParser(GoSoJsonParser parser) {
		this.parser = parser;
	}
}
