package com.example.guice_servlet.service;

import com.google.inject.servlet.RequestScoped;

@RequestScoped
public class Service implements IService {

	@Override
	public boolean foo() {
		return false;
	}

}
