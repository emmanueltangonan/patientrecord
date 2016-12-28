package org.eman.patientrecord.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "TestingFilter", urlPatterns = {"/*"})
public class TestingFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		String path = req.getRequestURI().substring(req.getContextPath().length());
		
		System.out.println(path);
		
		if(session== null && (!path.startsWith("/login.jsp"))){
			if(path.startsWith("/Login")){
				chain.doFilter(request, response);
				return;
			}else if(path.matches(".*(css|jpg|js|woff2|woff|ttf).*")){
			    chain.doFilter(request, response);
			    return;
			}else{
				res.sendRedirect(req.getContextPath() + "/login.jsp");
				return;
			}
		}
		else{
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

	public void destroy() {
		
	}
	
	
}
