package com.restaurant.restaurantservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.restaurant.restaurantservice.repository.RestaurantRepo;

@RestController
@RequestMapping("/restaurant")
public class RestaurantServiceController {
	
	@Autowired
	private RestaurantRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${restaurant.provider.uri:default uri}")
	private String greetingMessage;


	@GetMapping("/service")
	public String getServiceName() {
		return "you have called Restaurant Service";
	}

	@GetMapping("/config")
	public String callConfig() {
		return "my.greetings: " + greetingMessage + " db.connection ";
	}
	
	@GetMapping("/list")
	public List<String> restaurantList () throws Exception {
		return repo.restaurants();
	}
	
	@GetMapping("/order-status")
	@HystrixCommand(fallbackMethod = "defaultFunction", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10") })
		public String getOrderStatus() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http//localhost:8083/order", String.class);
		System.out.println(responseEntity.getBody());
		return responseEntity.getBody();
	}
	
	public String defaultFunction() {
		return "Server is down, please try later!!";
	}

}
