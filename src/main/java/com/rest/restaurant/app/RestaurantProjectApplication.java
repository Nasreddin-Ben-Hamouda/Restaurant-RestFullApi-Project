package com.rest.restaurant.app;


//import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;

//import com.rest.restaurant.app.repositories.UserRepository;
//import lombok.AllArgsConstructor;

@SpringBootApplication
//@AllArgsConstructor
public class RestaurantProjectApplication {

	    /*private UserRepository repository;
	      private PasswordEncoder encoder;

	    @PostConstruct
	    public void initUsers() {
	    	User user=new User();
	    	user.setEmail("nasreddinebenhamouda@gmail.com");
	    	user.setPassword(encoder.encode("hamzawi"));
	        repository.save(user);
	    }*/
	public static void main(String[] args) {
		SpringApplication.run(RestaurantProjectApplication.class, args);
	}

}
