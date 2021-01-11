package com.rest.restaurant.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipePerMonthResponse {
	private String month;
	private double recipe;
}
