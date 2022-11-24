package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "CART")
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("cartID")
	private long cartID;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> listOfProducts;
	private float totalAmount;
//	@OneToOne(mappedBy = "cart")
//	private Order order;

	
	public Cart(long id, float totalAmount) {
		super();
		this.cartID = id;
		this.totalAmount = totalAmount;
		this.listOfProducts = new ArrayList<Product>();
	}
	public long getId() {
		return cartID;
	}
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	
	public void addToCart(Product product) {
		listOfProducts.add(product);
		calculateAmount(product.getFinalPrice());
	}
	
	public void removeFromCart(Product product) {
		listOfProducts.remove(product);
		calculateAmount(-product.getFinalPrice());
	}
	
	private void calculateAmount(float productPrice) {
		this.totalAmount += productPrice;
	}
//	public void setOrder(Order order) {
//		this.order = order;
//	}
	
	
}
