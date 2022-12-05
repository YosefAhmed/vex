package com.example.demo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

	@Modifying
	@Transactional
	@Query(value="insert into cart_products(cartid, productid) VALUES (:cartID, :productID)", nativeQuery=true)
	void addItemToCart(long cartID, long productID);

	@Modifying
	@Transactional
	@Query(value="delete from cart_products where cartid=:cartID and productID=:productID", nativeQuery=true)
	void removeItemFromCart(long cartID, long productID);


}
