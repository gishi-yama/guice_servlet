package com.example.guice_servlet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

@Singleton
@WebServlet(urlPatterns = { "/bar" })
public class BarServlet extends GuiceInjectedServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter pr = resp.getWriter()) {
			pr.println(this.toString());
		}
	}

}
