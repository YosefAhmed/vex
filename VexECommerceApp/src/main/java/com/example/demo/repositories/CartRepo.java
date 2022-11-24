package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

}
