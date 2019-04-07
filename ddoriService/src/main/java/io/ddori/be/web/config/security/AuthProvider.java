package io.ddori.be.web.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.ddori.be.user.UserService;
import io.ddori.be.user.model.*;



@Component
public class AuthProvider implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthProvider.class);
	
	@Autowired
	UserService userService;
	//test
	
	public AuthProvider() {
		logger.debug("AuthProvider create");
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		logger.info("authenticate");
		
		String id = authentication.getName(); 
		String password = authentication.getCredentials().toString();
		
		HashMap param = new HashMap<String,String>();
		
		param.put("userId", id);
		List<UserInfo> userInfos = userService.getUserInfos(param);
	
		if (userInfos == null || userInfos.size() <=0){
			throw new BadCredentialsException("사용자 정보가 일치 하지 않습니다.");								
		}
		
		UserDetail user = createUser(userInfos.get(0));
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();        
        roles.addAll(user.getAuthorities());
        //PASSWORD 정보 알수 없음
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getId(), password, roles);
        token.setDetails(user);
        return token; 
		
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		//return false;
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	private UserDetail createUser(UserInfo userDto) {
		UserDetail loginUser = new UserDetail(userDto);
		String sAuthor = loginUser.getAuthor();
        if (sAuthor.equals("000")){
            //loginUser.setRoles(Arrays.asList("ROLE_ADMIN","ADMIN"));
        	loginUser.setRoles(Arrays.asList("ROLE_ADMIN"));
        }else {
        	loginUser.setRoles(Arrays.asList("ROLE_USER"));
        }
        return loginUser;
    }     
	
}
