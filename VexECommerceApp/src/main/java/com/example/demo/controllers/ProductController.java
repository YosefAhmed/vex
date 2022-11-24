package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/all_products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/product")
	public Optional<Product> getProduct(@RequestParam long productID){
		return productService.getProductByID(productID);
	}
	
	@PostMapping("/save_product")
	public String saveOrder(@RequestBody Product product) {
		productService.saveOrder(product);
		return "Saved Successfully";
	}
	
	@DeleteMapping("/remove_product")
	public String removeOrder(@RequestParam long productID) {
		productService.removeOrder(productID);
		return "Removed Successfully";
	}
}
