package com.rest.restaurant.app.services;

import java.util.List;

import com.rest.restaurant.app.dto.PeriodRequest;
import com.rest.restaurant.app.dto.RecipePerDayResponse;
import com.rest.restaurant.app.dto.RecipePerMonthResponse;
import com.rest.restaurant.app.models.Met;
import com.rest.restaurant.app.models.Ticket;

public interface TicketService {
	List<Ticket> findAll();
	Ticket save(Ticket ticket);
	Ticket finById(long number);
	Ticket edit(Ticket ticket,long number);
	Boolean delete(long number);
	Ticket addMetToTicket(Met met,long ticketId);
	Ticket removeMetFromTicket(Met met,long ticketId);
	List<Ticket> findAllTicketsByClient(long clientId);
	List<Ticket> findAllTicketsByTable(long tableId);
	List<RecipePerDayResponse> getRecipePerDay();
	List<RecipePerMonthResponse> getRecipePerMonth();
	double getRecipePerPeriod(PeriodRequest request);
}
