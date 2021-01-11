package com.rest.restaurant.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MostBuyableMainCourseResponse {
	
	private long id;
	private String name;
	private double price;
	private long count;

}
