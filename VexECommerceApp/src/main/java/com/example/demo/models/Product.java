package com.example.demo.models;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productID;
	private String name;
	private float price;
	private float discount;
	private String description;
//	private List<String> photos;
	
	public Product(long id, String name, float price, float discount, String description) {
		super();
		this.productID = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.description = description;
	}
	public long getProductID() {
		return productID;
	}
	public String getName() {
		return name;
	}
	public float getFinalPrice() {
		//return final price after discount
		return price - (price*discount);
	}
	public float getPrice() {
		return price;
	}
	public float getDiscount() {
		return discount;
	}
	public String getDescription() {
		return description;
	}
	
	
}
