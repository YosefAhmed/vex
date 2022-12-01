package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
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
	@PutMapping("/remove_item")
	public String removeFromCart(@RequestBody Cart cart, @RequestParam long productID, Model model, RedirectAttributes redirectAttributes) {
		Product product = cart.getProductByID(productID);
		if(product != null){
			cart.removeFromCart(product);
			editCart(cart);
		}
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("mycart", cart);
//		mv.setView(new RedirectView("cart"));
//		return mv;
		
		model.addAttribute("mycart", cart);
		
		return "cart";
		
	}
	@PutMapping("/add_item")
	public ModelAndView addToCart(@RequestBody Cart cart, @RequestParam Product product) {
		cart.addToCart(product);
		editCart(cart);
		ModelAndView mv = new ModelAndView();
		mv.addObject("mycart", cart);
		mv.setViewName("cart");
		return mv;
	}
	@PutMapping("/edit_cart")
	public void editCart(@RequestBody Cart cart) {
		saveCart(cart);
	}
	
	@GetMapping("/cart")
	public ModelAndView getCart(@RequestParam long cartID) {
		Optional<Cart> mycart = cartService.getCartByID(cartID);
		ModelAndView mv = new ModelAndView();
		mv.addObject("mycart", mycart.get());
		mv.setViewName("cart");
		return mv;
	}
}
