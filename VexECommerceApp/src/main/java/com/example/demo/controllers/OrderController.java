package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Order;
import com.example.demo.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order")
	public Optional<Order> getOrder(@RequestParam long orderID){
		return orderService.getOrderById(orderID);
	}
	
	@PostMapping("/save_order")
	public String saveOrder(@RequestBody Order order) {
		orderService.saveOrder(order);
		return "order saved successfully";
	}
	
}
