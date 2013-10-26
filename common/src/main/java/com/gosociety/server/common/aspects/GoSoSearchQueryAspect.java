package com.gosociety.server.common.aspects;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component("cacheAspect")
@Aspect
public class GoSoSearchQueryAspect {

	@Resource(name="gosoCache")
	private Cache cache;
	
	@Around("execution(* com.gosociety.server.common.dao.PlacesDao.findPlacesByDistance(java.lang.String,java.lang.String,java.lang.String,int,double))")
	public Object cacheSearchQuery(ProceedingJoinPoint pjp) throws Throwable {
		
		Object[] arguments = pjp.getArgs();  
        String searchkey = (String)arguments[0];  
        int offset = (Integer)arguments[3];
        
        Element element = cache.get(searchkey);
        
        if(element==null & offset>0) {
        	return null;
        }
        
        else if (element==null) {
        	
        	Object result = pjp.proceed();
        	
        	if(result != null) 
            {  
                element = new Element(searchkey, result);  
                cache.put(element);  
            }
        	
        	return result;
    	}
        
        else {
        	
        	element = cache.get(searchkey);
        	return element.getObjectValue();
        }
	}
}
