package com.rest.restaurant.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.restaurant.app.models.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
	@Query("select ta ,count(ta.id) as cnt"
			+ " from TableEntity ta INNER JOIN ta.tickets t"
			+ " GROUP BY ta.id"
			+ " ORDER BY cnt DESC")
	List<TableEntity> mostBookedTables();
}
