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

import com.rest.restaurant.app.dto.ClientDTO;
import com.rest.restaurant.app.models.Client;
import com.rest.restaurant.app.repositories.ClientRepository;
import com.rest.restaurant.app.services.ClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
	private ClientService clientService;
	private ModelMapper mapper;
	private ClientRepository rep;
	
	@GetMapping("/all")
	public List<ClientDTO> getAllClients(){
		
		return clientService.findAll()
						   .stream()
						   .map(client->mapper.map(client, ClientDTO.class))
						   .collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClientById(@PathVariable long id) {
		Client client=clientService.finById(id);
		if(client!=null)
			return new ResponseEntity<ClientDTO>(mapper.map(client, ClientDTO.class),HttpStatus.OK);
		else
			return new ResponseEntity<String>("client not found",HttpStatus.NOT_FOUND);

	}
	
	@PostMapping("/create")
	public ClientDTO createClient(@Valid @RequestBody ClientDTO client) {
		Client newClient=clientService.save(mapper.map(client, Client.class));	
		return mapper.map(newClient, ClientDTO.class);
	}
	
	@PutMapping("/{id}/edit")
	public ResponseEntity<?> editClient(@Valid @RequestBody ClientDTO clientDTO,@PathVariable long id) {
		Client client=clientService.edit(mapper.map(clientDTO, Client.class),id);	
		if(client!=null) 
			return new ResponseEntity<ClientDTO>(mapper.map(client, ClientDTO.class),HttpStatus.OK);
		else
			return new ResponseEntity<String>("client not found",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteClient(@PathVariable long id) {
		
		if(clientService.delete(id))
			return new ResponseEntity<String>("successfully operation",HttpStatus.OK);
		else
			return new ResponseEntity<String>("client not found",HttpStatus.NOT_FOUND);

	}
	@GetMapping("/mostClient")
	public ClientDTO getMostLoyalClient() {
		return mapper.map(clientService.getMostLoyalClient(), ClientDTO.class);
	}
	
	@GetMapping("/mostDay/{id}")
	public String getMostBookedDayByClient(@PathVariable long id) {
		return rep.mostBookedDayByClient(id);
	}
}
