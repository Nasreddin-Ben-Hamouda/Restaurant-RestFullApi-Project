package com.rest.restaurant.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.restaurant.app.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("select c ,count(c.id) as cnt"
			+ " from Client c INNER JOIN c.tickets t"
			+ " GROUP BY c.id"
			+ " ORDER BY cnt DESC")
	List<Client> mostLoyalClients();
	
	@Query(value ="SELECT  DAYNAME(created_at) as day ,count(number) as cnt"
			+ " from tickets t "
			+ " WHERE client_id=? "
			+ " GROUP BY day "
			+ " ORDER BY cnt DESC LIMIT 1",nativeQuery = true)
	String mostBookedDayByClient(long id);
}
