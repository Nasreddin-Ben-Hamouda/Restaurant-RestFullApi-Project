package com.rest.restaurant.app.dto;


import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PeriodRequest {
	
	@NotNull
	private LocalDate from;
	@NotNull
	private LocalDate to;

}
