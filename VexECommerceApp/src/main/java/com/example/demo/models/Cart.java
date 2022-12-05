package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "CART")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("cartID")
	private long cartID;
	@ManyToMany
	@JoinTable(
			name="Cart_Products", 
			joinColumns = @JoinColumn(name="cartID"), 
			inverseJoinColumns = @JoinColumn(name="productID"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"cartID", "productID"}))
	private List<Product> listOfProducts;
	private float totalAmount;
//	@OneToOne(mappedBy = "cart")
//	private Order order;

	public Cart() {
		this.listOfProducts = new ArrayList<Product>();
	}

	public long getCartID() {
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
	
	public Product getProductByID(long id) {
		for (Product product : listOfProducts) {
			if(product.getProductID() == id)
				return product;
		}
		return null;
	}
	
	private void calculateAmount(float productPrice) {
		this.totalAmount += productPrice;
	}
//	public void setOrder(Order order) {
//		this.order = order;
//	}
	
	
}
