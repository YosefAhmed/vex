package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.repositories.CartRepo;

@Service
public class CartService {

	@Autowired
	private CartRepo cartRepo;
	
	public void saveCart(Cart cart) {
		cartRepo.save(cart);
	}

	public Optional<Cart> getCartByID(long cartID) {
		return cartRepo.findById(cartID);
	}
	
}
