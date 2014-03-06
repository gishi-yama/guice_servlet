package com.example.guice_servlet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.guice_servlet.service.IService;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
@WebServlet(urlPatterns = { "/foo" })
public class FooServlet extends GuiceInjectedServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private Provider<IService> provider;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(this.toString());
		System.out.println(provider.get().toString());
		try (PrintWriter pr = resp.getWriter()) {
			pr.print(provider.get().foo());
		}
	}

}
