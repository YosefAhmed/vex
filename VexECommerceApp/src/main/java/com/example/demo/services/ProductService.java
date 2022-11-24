package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	public Optional<Product> getProductByID(long productId){
		return productRepo.findById(productId);
	}

	public void saveOrder(Product product) {
		productRepo.save(product);
	}

	public void removeOrder(long productID) {
		productRepo.deleteById(productID);		
	}
}
