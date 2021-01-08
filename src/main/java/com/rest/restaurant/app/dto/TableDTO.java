package com.rest.restaurant.app.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.rest.restaurant.app.models.TableType;

import lombok.Data;
@Data
public class TableDTO 
{
	
	private long number;
	@Max(8)
	@Min(1)
	@NotNull
	private int coverNumber;
	@NotNull
	private TableTypeDTO typeDTO;
	
	public void setType(TableType type) {
		ModelMapper mapper=new ModelMapper();
		this.typeDTO=mapper.map(type, TableTypeDTO.class);
	}


}
