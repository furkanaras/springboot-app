package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.model.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long>{

		
}
