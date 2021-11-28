package com.springboot.app.service;

import java.util.List;

import com.springboot.app.model.Customers;

public interface CustomerService {
	Customers saveCustomer(Customers employee);
	List<Customers> getAllCustomers();
	Customers getCustomerById(long id);
	Customers updateCustomer(Customers employee, long id);
	void deleteCustomer(long id);
}