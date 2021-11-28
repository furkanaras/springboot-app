package com.springboot.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.app.exception.ResourceNotFoundException;
import com.springboot.app.model.Customers;
import com.springboot.app.repository.CustomerRepository;
import com.springboot.app.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Customers saveCustomer(Customers customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customers> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customers getCustomerById(long id) {
//		Optional<Customer> customer = customerRepository.findById(id);
//		if(customer.isPresent()) {
//			return customer.get();
//		}else {
//			throw new ResourceNotFoundException("customer", "Id", id);
//		}
		return customerRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Customer", "Id", id));
		
	}

	@Override
	public Customers updateCustomer(Customers customer, long id) {
		
		// we need to check whether customer with given id is exist in DB or not
		Customers existingCustomer = customerRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 
		
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setTc(customer.getTc());
		// save existing customer to DB
		customerRepository.save(existingCustomer);
		return existingCustomer;
	}

	@Override
	public void deleteCustomer(long id) {
		
		// check whether a customer exist in a DB or not
		customerRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Employee", "Id", id));
		customerRepository.deleteById(id);
	}
	
}
