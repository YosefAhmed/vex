package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	
	public Optional<Order> getOrderById(long orderID){
		return orderRepo.findById(orderID);
	}

	public void saveOrder(Order order) {
		orderRepo.save(order);
	}
}

