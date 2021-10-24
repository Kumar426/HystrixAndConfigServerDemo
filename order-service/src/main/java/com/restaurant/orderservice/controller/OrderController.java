package com.restaurant.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@GetMapping
	public ResponseEntity<?> getOrderStatus () {
		int random = (int)(Math.random()*100);
		System.out.println(random);
		if (random % 2 == 0) {
			System.out.println("bad request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurd");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Payment Successful! Order Placed");
		}
	}

}
