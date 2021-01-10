package com.rest.restaurant.app.services;

import java.util.List;

import com.rest.restaurant.app.models.Client;

public interface ClientService {
	List<Client> findAll();
	Client save(Client client);
	Client finById(long id);
	Client edit(Client table,long id);
	Boolean delete(long id);
}
