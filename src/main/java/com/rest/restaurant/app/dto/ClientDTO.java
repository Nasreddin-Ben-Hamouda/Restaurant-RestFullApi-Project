package com.rest.restaurant.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClientDTO {

	private long id;
	
	@NotEmpty
	@Size(max = 50, min = 4)
	@Pattern(regexp = "[a-zA-Z ]+", message = "First Name must contain only characters")
	private String firstName;
	
	@NotEmpty
	@Size(max = 50, min = 4)
	@Pattern(regexp = "[a-zA-Z ]+", message = "Last Name must contain only characters")
	private String lastName;

	@Past
	private LocalDate birthday;
	
	@Size(max = 8,min = 8)
	@Pattern(regexp="[0-9]+",message = "phone number must contain only numbers")
	private String phone;
}
