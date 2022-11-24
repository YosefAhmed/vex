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

import com.example.demo.models.Cart;
import com.example.demo.services.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/save_cart")
	public String saveCart(@RequestBody Cart cart) {
		cartService.saveCart(cart);
		return "cart saved successfully";
	}
	
	@PutMapping("/edit_cart")
	public void editCart(@RequestBody Cart cart) {
		saveCart(cart);
	}
	
	@GetMapping("/cart")
	public Optional<Cart> getCart(@RequestParam long cartID) {
		return cartService.getCartByID(cartID);
	}
}
