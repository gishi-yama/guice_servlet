package com.example.guice_servlet.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.guice_servlet.service.IService;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class FooServletTest {

	private static final String output = "FooServletTest.txt";

	private HttpServletRequest req;
	private HttpServletResponse res;
	private ServletConfig config;

	@Before
	public void setUp() throws Exception {
		req = mock(HttpServletRequest.class);
		res = mock(HttpServletResponse.class);
		when(res.getWriter()).thenReturn(new PrintWriter(output));

		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				IService service = mock(IService.class);
				when(service.foo()).thenReturn(true);
				bind(IService.class).toInstance(service);
			}
		});

		ServletContext cont = mock(ServletContext.class);
		when(cont.getAttribute(Injector.class.getName())).thenReturn(injector);

		config = mock(ServletConfig.class);
		when(config.getServletContext()).thenReturn(cont);
	}

	@Before
	@After
	public void fileDelete() throws Exception {
		Files.deleteIfExists(Paths.get(output));
	}

	@Test
	public void trueが表示される() throws ServletException, IOException {
		FooServlet servlet = new FooServlet();
		servlet.init(config);
		servlet.doGet(req, res);
		assertEquals(
				Files.readAllLines(Paths.get(output), StandardCharsets.UTF_8).get(0),
				Boolean.TRUE.toString());
	}

}
