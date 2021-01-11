package com.rest.restaurant.app.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipePerDayResponse {

	private Date day;
	private double recipe;

}
