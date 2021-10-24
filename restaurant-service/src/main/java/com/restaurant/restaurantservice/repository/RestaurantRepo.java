package com.restaurant.restaurantservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RestaurantRepo  {
	
	public List<String> restaurants () throws Exception {
		List<String> restList = new ArrayList<>();
		try {
		
		restList.add("XYZ");
		restList.add("abc");
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//throw new RuntimeException();
		}
		return restList;
		
	}
	

}
