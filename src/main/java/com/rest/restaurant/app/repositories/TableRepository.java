package com.rest.restaurant.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.restaurant.app.models.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

}
