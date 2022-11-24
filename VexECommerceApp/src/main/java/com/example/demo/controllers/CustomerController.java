package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save_customer")
	public String saveCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return "customer saved successfully";
	}
	
	@GetMapping("/customer")
	public Optional<Customer> getCart(@RequestParam long customerID) {
		return customerService.getCustomerByID(customerID);
	}
}
