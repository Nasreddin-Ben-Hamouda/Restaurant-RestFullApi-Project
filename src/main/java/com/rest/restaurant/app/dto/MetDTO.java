package com.rest.restaurant.app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.rest.restaurant.app.models.Appetizer;
import com.rest.restaurant.app.models.MainCourse;
import com.rest.restaurant.app.models.Met;
import lombok.Data;

@Data
public class MetDTO {
	
	private long id;
	@NotEmpty
	@Size(max = 100, min = 4)
	@Pattern(regexp = "[a-zA-Z ]+", message = "First Name must contain only characters")
	private String name;
	
	@Positive
	@NotNull
	private double price;
	
	@NotNull
	@Min(1)
	@Max(3)
	private int type;
	public void setTypeDependMet(Met met) {
		if(met instanceof Appetizer) 
			this.type=1;
		else if (met instanceof MainCourse) 
			this.type=2;
		else
			this.type=3;
	}
	
}
