package io.ddori.be.web.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.ddori.be.common.model.ResultInfo;

public class CustomHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
    	response.setStatus(403);
    	response.setCharacterEncoding("utf-8"); 
    	
    	ResultInfo rs = new ResultInfo();
		rs.setFlag(ResultInfo.RESULT_FAIL);
		rs.setDesc("로그인 하여 주십시요.");
    	
    	//response.getWriter().print("로그인을 하여주십시.");
		ObjectMapper mapper = new ObjectMapper();
    	PrintWriter out = response.getWriter();			
		out.print(mapper.writeValueAsString(rs));
		out.flush(); 
		out.close(); 
    }

}
