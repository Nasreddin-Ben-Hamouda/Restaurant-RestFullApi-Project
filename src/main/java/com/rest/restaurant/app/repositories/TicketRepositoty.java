package com.rest.restaurant.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.restaurant.app.models.Ticket;

public interface TicketRepositoty extends JpaRepository<Ticket,Long>{

}
