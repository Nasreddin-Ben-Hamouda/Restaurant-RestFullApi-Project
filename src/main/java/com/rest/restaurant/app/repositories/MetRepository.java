package com.rest.restaurant.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.restaurant.app.models.Met;

@Transactional
public interface MetRepository extends JpaRepository<Met,Long> {
	
	@Modifying
	@Query(value = "update mets m set m.name = ?,m.price= ?,m.type=? where m.id = ?", 
	  nativeQuery = true)
	int updateMet(String name,double price,int type,long id);

}
