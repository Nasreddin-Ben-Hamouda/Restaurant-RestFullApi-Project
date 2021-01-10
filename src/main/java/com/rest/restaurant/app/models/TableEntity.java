package com.rest.restaurant.app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="tables")
public class TableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long number;
	
	@Column(name="cover_number")
	private int coverNumber;
	
	@ManyToOne
    @JoinColumn(name="type_id", nullable=false)
	private TableType type;
	
	@OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE ,fetch = FetchType.EAGER)
	private List<Ticket> tickets;
	
	/*public void setTypeDTO(TableTypeDTO type) {
		ModelMapper mapper=new ModelMapper();
		this.type=mapper.map(type, TableType.class);
	}*/

	
}
