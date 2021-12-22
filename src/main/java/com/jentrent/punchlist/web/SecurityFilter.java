package com.jentrent.punchlist.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jentrent.punchlist.entity.Account;

public class SecurityFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

		HttpServletRequest req = (HttpServletRequest) request;

		if(req.getSession().getAttribute("account") == null){
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}else{

			Account account = (Account) req.getSession().getAttribute("account");
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException{

	}

	public void destroy(){

	}

}
