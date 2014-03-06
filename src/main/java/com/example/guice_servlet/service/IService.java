package com.example.guice_servlet.service;

import com.google.inject.ImplementedBy;

@ImplementedBy(Service.class)
public interface IService {

	public boolean foo();

}
