package com.rest.restaurant.app.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name",length =20,nullable = false)
	private String firstName;
	
	@Column(name="last_name",length =20,nullable = false)
	private String lastName;
	
	private LocalDate birthday;
	
	private int phone;
	
	@OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE ,fetch = FetchType.EAGER)
	private List<Ticket> tickets;

}
