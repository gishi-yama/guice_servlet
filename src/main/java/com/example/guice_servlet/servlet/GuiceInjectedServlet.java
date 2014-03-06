package com.example.guice_servlet.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.inject.Injector;

public class GuiceInjectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		Injector injector = (Injector) config.getServletContext().getAttribute(Injector.class.getName());
		if (injector == null) {
			throw new ServletException("Guice Injector not found");
		}
		System.out.println(injector.toString());
		injector.injectMembers(this);
	}

}
