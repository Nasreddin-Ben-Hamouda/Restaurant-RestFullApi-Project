package com.rest.restaurant.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.restaurant.app.models.Client;
import com.rest.restaurant.app.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	@Override
	public Client save(Client client) {
		return clientRepo.save(client);
	}

	@Override
	public Client finById(long id) {
		Optional<Client> client=clientRepo.findById(id);
		if(client.isPresent())
			return client.get();
		else
			return null;
	}

	@Override
	public Client edit(Client client, long id) {
		Client oldClient=this.finById(id);
		if(oldClient!=null) {
			oldClient.setFirstName(client.getFirstName());
			oldClient.setLastName(client.getLastName());
			oldClient.setPhone(client.getPhone());
			oldClient.setBirthday(client.getBirthday());
			return clientRepo.save(oldClient);
		}
		return null;
	}

	@Override
	public Boolean delete(long id) {
		Client client=this.finById(id);
		if(client!=null) {
			clientRepo.delete(client);
			return true;
		}
		return false;
	}

	@Override
	public Client getMostLoyalClient() {
		List<Client> clients=clientRepo.mostLoyalClients();
		if(clients.size()>0)
			return clients.get(0);
		else
			return null;
	}

}
