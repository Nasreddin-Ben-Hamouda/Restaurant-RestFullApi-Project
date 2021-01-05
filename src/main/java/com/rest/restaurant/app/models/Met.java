package com.rest.restaurant.app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity(name="mets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="mets")
public abstract class Met {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(length = 40,nullable = false,unique = true)
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	@ManyToMany(mappedBy = "mets",cascade = CascadeType.REMOVE)
	private List<Ticket> Tickets;

}
