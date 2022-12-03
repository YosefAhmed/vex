package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.ApplicationController;
import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.services.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	public  Cart globalCart;
	
	@PostMapping("/save_cart")
	public String saveCart(@RequestBody Cart cart) {
		cart = cartService.saveCart(cart);
		return "cart saved successfully";
	}
	
	
	@PutMapping("/add_item")
	public String addToCart(@RequestBody Product product, Model model) {
		if(globalCart == null) {
			globalCart = cartService.saveCart(new Cart());
		}
		globalCart.addToCart(product);
		editCart(globalCart);
		model.addAttribute("mycart", globalCart);
		return "redirect:/cart";
	}

	
	
	@PutMapping("/remove_item")
	public String removeFromCart(@RequestBody Product product, Model model) {
		if(globalCart != null) {
			globalCart.removeFromCart(product);
			if(globalCart.getListOfProducts().isEmpty()) {
				deleteCart(globalCart.getCartID());
				globalCart = null;
			}
			else {
				editCart(globalCart);
			}
		}
		model.addAttribute("mycart", globalCart);
		return "redirect:/cart";
	}
	
	
	@GetMapping("/cart")
	public ModelAndView getCart(@RequestParam long cartID) {
		globalCart = cartService.getCartByID(cartID).get();
		ModelAndView mv = new ModelAndView();
		mv.addObject("mycart", globalCart);
		mv.setViewName("cart");
		return mv;
	}
	
//	@PutMapping("/edit_cart")
	public void editCart(@RequestBody Cart cart) {
		saveCart(cart);
	}
	@DeleteMapping("/delete_cart")
	public void deleteCart(@RequestParam long cartID) {
		cartService.deleteCart(cartID);
	}
}
