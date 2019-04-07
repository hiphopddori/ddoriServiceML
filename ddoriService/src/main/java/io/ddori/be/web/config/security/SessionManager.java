package io.ddori.be.web.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
	
private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
	
	public String getUserName(){		
		String userName = "";	
		try 
		{
			UserDetail user = (UserDetail)SecurityContextHolder.getContext().getAuthentication().getDetails();
			userName = user.getUsername();
		} 
		catch (Exception e) 
		{
			logger.error("Error: getUserName", e);
		}		
		return userName;		
	}	
	
	public String getUserId(){
		String userId = "";	
		try 
		{
			UserDetail user = (UserDetail)SecurityContextHolder.getContext().getAuthentication().getDetails();
			userId = user.getId();
			
		} 
		catch (Exception e) 
		{
			logger.error("Error: getUserId", e);
		}		
		return userId;		
	}
	
	/* 사용자 정보 를 얻는다.
	 */
	public UserDetail getUserInfo(){
		
		UserDetail userInfo = null;		
		try {
			userInfo = (UserDetail)SecurityContextHolder.getContext().getAuthentication().getDetails();
		} catch (Exception e) {
			logger.error("Error: getUserInfo", e);
		}		
		return userInfo;
		
	}
	
}
