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
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Cart saveCart(@RequestBody Cart cart) {
		return cartService.saveCart(cart);
	}
	
	
	@PutMapping("/add_item")
	public String addToCart(@RequestBody Product product, Model model) {
		if(globalCart == null) {
			globalCart = saveCart(new Cart());
		}
		globalCart.addToCart(product);
		_addItemToCart(product.getProductID());
		model.addAttribute("mycart", globalCart);
		return "redirect:/cart";
	}

	private void _addItemToCart(long productID) {
		cartService.addItemToCart(globalCart.getCartID(), productID);
	}


	@PutMapping("/remove_item")
	public String removeFromCart(@RequestParam long productID, Model model) {
		Product product = globalCart.getProductByID(productID);
		if(globalCart != null) {
			globalCart.removeFromCart(product);
			_removeItemFromCart(product.getProductID());
		}
		model.addAttribute("mycart", globalCart);
		return "redirect:/cart";
	}
	
	
	private void _removeItemFromCart(long productID) {
		cartService.removeItemFromCart(globalCart.getCartID(), productID);
	}


	@RequestMapping("/cart")
	public ModelAndView getCart() {
		if(globalCart==null) {
			globalCart = saveCart(new Cart());
		}
		else {
			globalCart = cartService.getCartByID(globalCart.getCartID()).get();			
		}
		ModelAndView mv = new ModelAndView("cart");
		mv.addObject("mycart", globalCart);
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
