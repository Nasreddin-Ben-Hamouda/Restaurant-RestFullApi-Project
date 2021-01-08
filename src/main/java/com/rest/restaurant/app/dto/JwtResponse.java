package com.rest.restaurant.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	
	private String token;
	private long id;
	private String email;
	
}
