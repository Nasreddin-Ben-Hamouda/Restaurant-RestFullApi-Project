package com.rest.restaurant.app.services;

import java.util.List;

import com.rest.restaurant.app.models.TableType;

public interface TableTypeService {
	
	List<TableType> findAll();
	TableType save(TableType type);
	TableType finById(long id);
	TableType edit(TableType type,long id);
	Boolean delete(long id);
	
	

}
