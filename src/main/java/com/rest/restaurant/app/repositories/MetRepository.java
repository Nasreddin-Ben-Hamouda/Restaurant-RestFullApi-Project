package com.rest.restaurant.app.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rest.restaurant.app.dto.MostBuyableMainCourseResponse;
import com.rest.restaurant.app.models.Met;

@Transactional
public interface MetRepository extends JpaRepository<Met,Long> {
	
	@Modifying
	@Query(value = "update mets m set m.name = ?,m.price= ?,m.type=? where m.id = ?", 
	  nativeQuery = true)
	int updateMet(String name,double price,int type,long id);
	
	@Query("select"
			+ " new com.rest.restaurant.app.dto.MostBuyableMainCourseResponse(m.id,m.name,m.price,count(m.id) as cnt)"
			+ " from Met m INNER JOIN m.tickets t"
			+ " WHERE type(m) = MainCourse"
			+ "	AND t.createdAt BETWEEN :from AND :to"
			+ " GROUP BY m.id"
			+ " ORDER BY cnt DESC")
	List<MostBuyableMainCourseResponse> mostBuyableMainCourses(@Param("from") LocalDateTime from,@Param("to") LocalDateTime to,Pageable pageable);
}
