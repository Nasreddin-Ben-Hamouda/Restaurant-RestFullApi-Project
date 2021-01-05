package com.rest.restaurant.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.restaurant.app.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
