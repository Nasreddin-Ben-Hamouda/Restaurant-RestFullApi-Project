package com.rest.restaurant.app.models;

import java.time.LocalDateTime;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long number;
	
	@Column(name="created_at",nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="cover_number")
	private int coverNumber;
	
	private double addition;
	
	@ManyToOne
    @JoinColumn(name="client_id", nullable=false)
	private Client client;
	
	@ManyToOne
    @JoinColumn(name="table_id", nullable=false)
	private TableEntity table;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tickets_mets",
	joinColumns = @JoinColumn(name="ticket_id"),
    inverseJoinColumns = @JoinColumn(name = "met_id"))
	private List<Met> mets;
	
}
