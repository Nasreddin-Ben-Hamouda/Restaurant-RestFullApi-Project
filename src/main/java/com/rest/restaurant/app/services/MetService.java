package com.rest.restaurant.app.services;

import java.util.List;
import com.rest.restaurant.app.models.Met;

public interface MetService {
	List<Met> findAll();
	Met save(Met met);
	Met finById(long id);
	Met edit(Met met,int type,long id);
	Boolean delete(long id);
}
