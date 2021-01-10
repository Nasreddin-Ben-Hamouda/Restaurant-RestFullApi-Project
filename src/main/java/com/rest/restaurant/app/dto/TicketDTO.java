package com.rest.restaurant.app.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.rest.restaurant.app.models.Client;
import com.rest.restaurant.app.models.Met;
import com.rest.restaurant.app.models.TableEntity;

import lombok.Data;

@Data
public class TicketDTO {

	private long number;
	
	private LocalDateTime createdAt;
	
	@Max(8)
	@Min(1)
	@NotNull
	private int coverNumber;
	
	private double addition;
	
	@NotNull
	private ClientDTO clientDTO;
	@NotNull
	private TableDTO tableDTO;
	
	private List<MetDTO> metsDTO;
	public void setClient(Client client) {
		ModelMapper mapper=new ModelMapper();
		this.clientDTO=mapper.map(client, ClientDTO.class);
	}
	public void setTable(TableEntity table) {
		ModelMapper mapper=new ModelMapper();
		this.tableDTO=mapper.map(table, TableDTO.class);
	}
	public void setMets(List<Met> mets) {
		ModelMapper mapper=new ModelMapper();
		this.metsDTO= mets.stream()
						   .map(met->{
								   MetDTO metDTO=mapper.map(met, MetDTO.class);
								   metDTO.setTypeDependMet(met);
								   return metDTO;
							   })
						   .collect(Collectors.toList());
	}
}
