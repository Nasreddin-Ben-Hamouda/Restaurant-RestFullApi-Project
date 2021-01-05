package com.rest.restaurant.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.restaurant.app.models.TableType;

public interface TableTypeRepository extends JpaRepository<TableType, Long> {

}
