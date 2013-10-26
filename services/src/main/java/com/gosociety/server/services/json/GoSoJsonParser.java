package com.gosociety.server.services.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public interface GoSoJsonParser {
	
	public Object parse(String s) throws JsonParseException, JsonMappingException, IOException;

}
