package com.rest.restaurant.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rest.restaurant.app.dto.JwtResponse;
import com.rest.restaurant.app.models.User;
import com.rest.restaurant.app.util.JwtUtil;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
   

    @GetMapping("/hello")
    public String welcome() {
        return "Welcome to restaurant !!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user) throws Exception {
    	
    	Authentication auth;
    	try {
                    auth=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (Exception ex) {
            return new ResponseEntity<String>("invalid username/password",HttpStatus.NOT_FOUND);
        }
    	String jwt = jwtUtil.generateToken(user.getEmail());
        User userDetails=(User) auth.getPrincipal();
        return new ResponseEntity<JwtResponse>(new JwtResponse(jwt,userDetails.getId(),userDetails.getEmail()),HttpStatus.OK);
     
    }
  

}
