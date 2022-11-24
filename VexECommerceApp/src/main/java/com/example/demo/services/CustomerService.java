package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	public void saveCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	public Optional<Customer> getCustomerByID(long customerID) {
		return customerRepo.findById(customerID);
	}
}
