package com.springboot.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.model.Customers;
import com.springboot.app.service.CustomerService;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	// build create customer REST API
	@PostMapping()
	public ResponseEntity<Customers> saveCustomer(@RequestBody Customers customer){
		return new ResponseEntity<Customers>(customerService.saveCustomer(customer), HttpStatus.CREATED);
	}
	
	// build get all customers REST API
	@GetMapping
	public List<Customers> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	// build get customer by id REST API
	// http://localhost:8080/api/customers/1
	@GetMapping("{id}")
	public ResponseEntity<Customers> getCustomerById(@PathVariable("id") long customerId){
		return new ResponseEntity<Customers>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	// build update customer REST API
	// http://localhost:8080/api/customers/1
	@PutMapping("{id}")
	public ResponseEntity<Customers> updateCustomer(@PathVariable("id") long id
												  ,@RequestBody Customers customer){
		return new ResponseEntity<Customers>(customerService.updateCustomer(customer, id), HttpStatus.OK);
	}
	
	// build delete customer REST API
	// http://localhost:8080/api/customers/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id){
		
		// delete customer from DB
		customerService.deleteCustomer(id);
		
		return new ResponseEntity<String>("customer deleted successfully!.", HttpStatus.OK);
	}
	
}