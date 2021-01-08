package com.rest.restaurant.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.restaurant.app.models.TableType;
import com.rest.restaurant.app.repositories.TableTypeRepository;

@Service
public class TableTypeServiceImpl implements TableTypeService {
	
	@Autowired
	private TableTypeRepository typeRepo;

	@Override
	public List<TableType> findAll() {
		return typeRepo.findAll();
	}

	@Override
	public TableType save(TableType type) {
		return typeRepo.save(type);
	}

	@Override
	public TableType finById(long id) {
			Optional<TableType> type=typeRepo.findById(id);
			if(type.isPresent())
				return type.get();
			else
				return null;
	}

	@Override
	public TableType edit(TableType type, long id) {
		TableType oldType=this.finById(id);
		if(oldType!=null) {
			//BeanUtils.copyProperties(type, oldType);
			oldType.setTitle(type.getTitle());
			oldType.setSupplement(type.getSupplement());
			return typeRepo.save(oldType);
		}
		return null;
	}

	@Override
	public Boolean delete(long id) {
		TableType type=this.finById(id);
		if(type!=null) {
			typeRepo.delete(type);
			return true;
		}
		return false;
	}

}
