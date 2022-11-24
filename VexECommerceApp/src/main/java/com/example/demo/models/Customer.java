package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("customerID")
	private long customerID;
	private String name;
	private String address;
	
//	@OneToOne(mappedBy = "customer")
//	private Order order;
	
	public Customer(long id, String name, String address, Order order) {
		super();
		this.customerID = id;
		this.name = name;
		this.address = address;
	}
	public long getId() {
		return customerID;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//	public Order getOrder() {
//		return order;
//	}

	
	
}
