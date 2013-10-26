package com.gosociety.server.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.model.EventBasic;

@Controller
@RequestMapping("/test")
public class GoSoTestController {
	
	@RequestMapping(value="/owners/{ownerId}",method = RequestMethod.POST)
	@ResponseBody
	public EventBasic test(@PathVariable String ownerId) {
		System.out.println(ownerId);
		return new EventBasic();
	}
}
