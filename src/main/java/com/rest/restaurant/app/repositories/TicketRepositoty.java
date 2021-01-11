package com.rest.restaurant.app.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest.restaurant.app.dto.RecipePerDayResponse;
import com.rest.restaurant.app.dto.RecipePerMonthResponse;
import com.rest.restaurant.app.dto.RecipePerWeekResponse;
import com.rest.restaurant.app.models.Ticket;

public interface TicketRepositoty extends JpaRepository<Ticket,Long>{
	@Query("SELECT new com.rest.restaurant.app.dto.RecipePerDayResponse(Date(t.createdAt) as day ,SUM(t.addition) as ad) "
			+ "from Ticket t "
			+ "GROUP by day")
	List<RecipePerDayResponse> getRecipePerDay();
	
	@Query("SELECT new com.rest.restaurant.app.dto.RecipePerMonthResponse(DATE_FORMAT(t.createdAt,'%Y-%m') as mnt ,SUM(t.addition) as ad) "
			+ "from Ticket t "
			+ "GROUP by mnt")
	List<RecipePerMonthResponse> getRecipePerMonth();
	
	@Query("SELECT SUM(t.addition) as ad "
			+ "from Ticket t "
			+ "where t.createdAt BETWEEN :from AND :to")
	double getRecipePerPeriod(@Param("from") LocalDateTime from,@Param("to") LocalDateTime to);
	
	@Query(value = "SELECT EXTRACT(WEEK FROM t.created_at) as week ,SUM(t.addition) as recipe "
			+ "from tickets t "
			+ "GROUP by week",nativeQuery = true)
	List<RecipePerWeekResponse> getRecipePerWeek();
	
}
