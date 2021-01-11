package com.rest.restaurant.app.services;

import java.util.List;

import com.rest.restaurant.app.models.TableEntity;

public interface TableService {
	List<TableEntity> findAll();
	TableEntity save(TableEntity table);
	TableEntity finById(long id);
	TableEntity edit(TableEntity table,long id);
	Boolean delete(long id);
	TableEntity getMostBookedTable();

}
