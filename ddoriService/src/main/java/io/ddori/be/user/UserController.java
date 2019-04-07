package io.ddori.be.user;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ddori.be.common.model.ResultInfo;
import io.ddori.be.user.model.UserInfo;
import io.ddori.be.web.config.security.AuthProvider;

@RequestMapping("/api/user/*")
@RestController
public class UserController {
	
	//@Autowired
	//AuthProvider authProvider;
	
	@Resource(name="authenticationManager")
	private AuthenticationManager authManager;
	
	@Autowired
	UserService service;
	@PostMapping("/userInfos")
	public ResultInfo getUserInfos(HttpServletRequest request){		
		
		ResultInfo rs = new ResultInfo();
		HashMap param = new HashMap<String,Object>();
	    param.put("userId", "sunsee78");
	    List<UserInfo> users = service.getUserInfos(param);
	   
	    rs.setResult(users);
		return rs;		
	}
	
	
	@PostMapping("/login" )
	public ResultInfo login(HttpServletRequest request, @RequestBody HashMap param) {
		
		ResultInfo rs = new ResultInfo();
		
		String loginId = (String)param.get("loginId");
		String password = (String)param.get("password");
		
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginId, password);
		 try {
			 
			 
			 Authentication auth = authManager.authenticate(authReq);
			 
		     SecurityContext sc = SecurityContextHolder.getContext();
		     sc.setAuthentication(auth);
		     HttpSession session = request.getSession(true);
		     session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
		     
		     rs.setFlag(ResultInfo.RESULT_SUCCESS);
		     
		 } catch (AuthenticationException e){
			 rs.setDesc(e.getMessage());
			 rs.setFlag(ResultInfo.RESULT_FAIL);
		 }
		 
		 return rs;
		
	}
	@GetMapping("/logout" )
	public ResultInfo logout(HttpServletRequest request , HttpServletResponse response) {
		
		ResultInfo rs = new ResultInfo();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    
	    rs.setFlag(ResultInfo.RESULT_SUCCESS);
		
		return rs;
	}
	
	
}
