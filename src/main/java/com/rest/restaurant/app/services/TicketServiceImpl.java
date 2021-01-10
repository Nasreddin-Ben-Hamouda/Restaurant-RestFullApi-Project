package com.rest.restaurant.app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.rest.restaurant.app.models.Client;
import com.rest.restaurant.app.models.Met;
import com.rest.restaurant.app.models.TableEntity;
import com.rest.restaurant.app.models.Ticket;
import com.rest.restaurant.app.repositories.TicketRepositoty;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{


	private TicketRepositoty ticketRepo;
	private ClientService clientService;
	private TableService tableService;
	private MetService metService;
	
	@Override
	public List<Ticket> findAll() {
		return ticketRepo.findAll();
	}

	@Override
	public Ticket save(Ticket ticket) {
		Ticket newTicket=ticketRepo.save(ticket);
		newTicket=this.finById(newTicket.getNumber());
		double addition=0;
		for(Met met:newTicket.getMets()) {
			addition+=met.getPrice();
		}
	    addition+=newTicket.getTable().getType().getSupplement();
		newTicket.setAddition(addition);
		
		return ticketRepo.save(newTicket);
	}

	@Override
	public Ticket finById(long number) {
		Optional<Ticket> ticket=ticketRepo.findById(number);
		if(ticket.isPresent())
			return ticket.get();
		else
			return null;

	}

	@Override
	public Ticket edit(Ticket ticket, long number) {
		Ticket oldTicket=this.finById(number);
		if(oldTicket!=null) {
			oldTicket.setCoverNumber(ticket.getCoverNumber());
			oldTicket.setClient(ticket.getClient());
			oldTicket.setTable(ticket.getTable());
			oldTicket.setMets(ticket.getMets());
			oldTicket=ticketRepo.save(oldTicket);
			double addition=0;
			for(Met met:oldTicket.getMets()) {
				addition+=met.getPrice();
			}
			addition+=oldTicket.getTable().getType().getSupplement();
			oldTicket.setAddition(addition);
			return ticketRepo.save(oldTicket);
		}
		return null;
	}

	@Override
	public Boolean delete(long number) {
		Ticket ticket=this.finById(number);
		if(ticket!=null) {
			ticketRepo.delete(ticket);
			return true;
		}
		return false;
	}

	@Override
	public Ticket addMetToTicket(Met met, long ticketId) {
		Ticket ticket=this.finById(ticketId);
		if(ticket!=null) {
			Met metFinded=metService.finById(met.getId());
			if(metFinded!=null) {
				ticket.getMets().add(metFinded);
				ticket.setAddition(ticket.getAddition()+metFinded.getPrice());
			}
				return ticketRepo.save(ticket);
		}
		return null;
	}

	@Override
	public Ticket removeMetFromTicket(Met met, long ticketId) {
		Ticket ticket=this.finById(ticketId);
		if(ticket!=null) {
			Met metFinded=metService.finById(met.getId());
			if(metFinded!=null) {
				for(Met m:ticket.getMets()) {
					if(m.getId()==metFinded.getId()) {
						ticket.getMets().remove(m);
						break;
					}
				}
				ticket.setAddition(ticket.getAddition()-metFinded.getPrice());
			}
			
			return ticketRepo.save(ticket);
		}
		return null;
	}

	@Override
	public List<Ticket> findAllTicketsByClient(long clientId) {
		Client client=clientService.finById(clientId);
		if(client!=null) {
			return client.getTickets();
		}
		return null;
	}

	@Override
	public List<Ticket> findAllTicketsByTable(long tableId) {
		TableEntity table=tableService.finById(tableId);
		if(table!=null) {
			return table.getTickets();
		}
		return null;
	}

}
