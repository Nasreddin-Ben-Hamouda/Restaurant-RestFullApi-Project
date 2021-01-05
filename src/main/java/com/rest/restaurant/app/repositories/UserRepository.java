package com.rest.restaurant.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.restaurant.app.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
