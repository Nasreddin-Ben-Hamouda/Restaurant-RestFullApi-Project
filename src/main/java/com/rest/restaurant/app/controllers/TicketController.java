package com.rest.restaurant.app.controllers;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.restaurant.app.dto.MetDTO;
import com.rest.restaurant.app.dto.PeriodRequest;
import com.rest.restaurant.app.dto.RecipePerDayResponse;
import com.rest.restaurant.app.dto.RecipePerMonthResponse;
import com.rest.restaurant.app.dto.TicketDTO;
import com.rest.restaurant.app.models.Met;
import com.rest.restaurant.app.models.Ticket;
import com.rest.restaurant.app.repositories.TicketRepositoty;
import com.rest.restaurant.app.dto.RecipePerWeekResponse;
import com.rest.restaurant.app.services.TicketService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
	private TicketService ticketService;
	private ModelMapper mapper;
	private TicketRepositoty rep;
	
	@GetMapping("/all")
	public List<TicketDTO> getAllTickets(){
		
		return ticketService.findAll()
						   .stream()
						   .map(ticket->{
							  TicketDTO ticketDTO=mapper.map(ticket, TicketDTO.class);
							  return ticketDTO;
							   })
						   .collect(Collectors.toList());
	}
	@GetMapping("/{number}")
	public ResponseEntity<?> getTableById(@PathVariable long number) {
		Ticket ticket=ticketService.finById(number);
		if(ticket!=null) {
			return new ResponseEntity<TicketDTO>(mapper.map(ticket, TicketDTO.class),HttpStatus.OK);
		}

		return new ResponseEntity<String>("ticket not found",HttpStatus.NOT_FOUND);

	}
	@PostMapping("/create")
	public TicketDTO createTable(@Valid @RequestBody TicketDTO ticket) {
		Ticket newTicket=ticketService.save(mapper.map(ticket, Ticket.class));	
		return mapper.map(newTicket, TicketDTO.class);
	}
	@PutMapping("{number}/edit")
	public ResponseEntity<?> editTicket(@Valid @RequestBody TicketDTO ticketDTO,@PathVariable long number) {
		Ticket ticket=ticketService.edit(mapper.map(ticketDTO, Ticket.class),number);	
		if(ticket!=null) 	
			return new ResponseEntity<TicketDTO>(mapper.map(ticket, TicketDTO.class),HttpStatus.OK);
		else
			return new ResponseEntity<String>("table not found",HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/{number}/delete")
	public ResponseEntity<?> deleteTable(@PathVariable long number) {
		
		if(ticketService.delete(number))
			return new ResponseEntity<String>("successfully operation",HttpStatus.OK);
		else
			return new ResponseEntity<String>("table not found",HttpStatus.NOT_FOUND);

	}
	@PostMapping("{number}/addMet")
	public TicketDTO addMetToTicket(@RequestBody MetDTO metDTO,@PathVariable long number) {
		Ticket ticket=ticketService.addMetToTicket(mapper.map(metDTO,Met.class),number);	
		return mapper.map(ticket, TicketDTO.class);
	}
	
	@DeleteMapping("{number}/removeMet")
	public TicketDTO removeMetFromTicket(@RequestBody MetDTO metDTO,@PathVariable long number) {
		Ticket ticket=ticketService.removeMetFromTicket(mapper.map(metDTO,Met.class),number);	
		return mapper.map(ticket, TicketDTO.class);
	}
	@GetMapping("/client/{clientId}")
	public List<TicketDTO> findAllTicketsByClient(@PathVariable long clientId) {
		return ticketService.findAllTicketsByClient(clientId)
				   .stream()
				   .map(ticket->{
					  TicketDTO ticketDTO=mapper.map(ticket, TicketDTO.class);
					  return ticketDTO;
					   })
				   .collect(Collectors.toList());
	}
	@GetMapping("/table/{tableId}")
	public List<TicketDTO> findAllTicketsByTable(@PathVariable long tableId) {
		return ticketService.findAllTicketsByTable(tableId)
				   .stream()
				   .map(ticket->{
					  TicketDTO ticketDTO=mapper.map(ticket, TicketDTO.class);
					  return ticketDTO;
					   })
				   .collect(Collectors.toList());
	}
	
	@GetMapping("/recipePerDay")
	public List<RecipePerDayResponse> getRecipePerDay() {
		return ticketService.getRecipePerDay();
	}
	@GetMapping("/recipePerMonth")
	public List<RecipePerMonthResponse> getRecipePerMonth() {
		return ticketService.getRecipePerMonth();
	}
	@PostMapping("/recipePerPeriod")
	public double getRecipePerPeriod(@RequestBody PeriodRequest request) {
		return ticketService.getRecipePerPeriod(request);
	}
	@GetMapping("/recipePerWeek")
	public List<RecipePerWeekResponse> getRecipePerWeek() {
		return rep.getRecipePerWeek();
	}
	
}
