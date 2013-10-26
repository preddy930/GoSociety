package com.gosociety.server.services.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.gosociety.server.services.model.GoSoBingReverseGeoCodeResponse;

public class GoSoBingReverseGeoCodeParser implements GoSoJsonParser {
	
	@Override
	public GoSoBingReverseGeoCodeResponse parse(String s) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GoSoBingReverseGeoCodeResponse rs = mapper.readValue(s, GoSoBingReverseGeoCodeResponse.class);
		
		return rs;
	}
}
