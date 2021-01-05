package com.rest.restaurant.app.models;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="types")
public class TableType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(length = 50,nullable = false)
	private String title;
	
	@Column(nullable = false)
	private double supplement;
	
	@OneToMany(mappedBy = "type",cascade = CascadeType.REMOVE )
	private List<TableEntity> tables;
	
}
