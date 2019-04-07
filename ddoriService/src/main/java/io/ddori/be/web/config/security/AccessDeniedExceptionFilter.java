package io.ddori.be.web.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

public class AccessDeniedExceptionFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws ServletException, IOException {
        
    	try {
            fc.doFilter(req, res);
        } catch (AccessDeniedException e) {
         // log error if needed here then redirect
        	String redirecturl ="";
        	RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(redirecturl);
        	requestDispatcher.forward(req, res);
        }
    }
}
