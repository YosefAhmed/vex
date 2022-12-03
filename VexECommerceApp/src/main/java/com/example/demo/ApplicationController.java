package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Cart;

@Controller
public class ApplicationController {
	
	@RequestMapping("/home")
	public String Home() {
		return "index";
	}
}
