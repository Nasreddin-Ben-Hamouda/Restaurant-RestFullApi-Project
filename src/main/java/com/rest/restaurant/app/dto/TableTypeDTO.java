package com.rest.restaurant.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;


@Data
public class TableTypeDTO {


	private long id;
	@NotEmpty
	private String title;
	@Positive
	@NotNull
	private double supplement;
	
}
