package com.rest.restaurant.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.restaurant.app.models.Met;

public interface MetRepository extends JpaRepository<Met,Long> {

}
