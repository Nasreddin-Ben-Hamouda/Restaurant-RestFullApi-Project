package com.rest.restaurant.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.restaurant.app.dto.TableTypeDTO;
import com.rest.restaurant.app.models.TableType;
import com.rest.restaurant.app.services.TableTypeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/table/type")
public class TableTypeController {
	
	private TableTypeService typeService;
	private ModelMapper mapper;
	
	@GetMapping("/all")
	public List<TableTypeDTO> getAllTableType(){
		
		return typeService.findAll()
						   .stream()
						   .map(type->mapper.map(type, TableTypeDTO.class))
						   .collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTableTypeById(@PathVariable long id) {
		TableType type=typeService.finById(id);
		if(type!=null)
			return new ResponseEntity<TableTypeDTO>(mapper.map(type, TableTypeDTO.class),HttpStatus.OK);
		else
			return new ResponseEntity<String>("table type not found",HttpStatus.NOT_FOUND);

	}
	
	@PostMapping("/create")
	public TableTypeDTO createTableType(@Valid @RequestBody TableTypeDTO type) {
		TableType newType=typeService.save(mapper.map(type, TableType.class));
		return mapper.map(newType, TableTypeDTO.class);
	}
	
	@PutMapping("/{id}/edit")
	public ResponseEntity<?> editTableType(@Valid @RequestBody TableTypeDTO typeDTO,@PathVariable long id) {
		TableType type=typeService.edit(mapper.map(typeDTO,TableType.class), id);
		if(type!=null) 
			return new ResponseEntity<TableTypeDTO>(mapper.map(type, TableTypeDTO.class),HttpStatus.OK);
		else
			return new ResponseEntity<String>("table type not found",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteTableType(@PathVariable long id) {
		
		if(typeService.delete(id))
			return new ResponseEntity<String>("successfully operation",HttpStatus.OK);
		else
			return new ResponseEntity<String>("table type not found",HttpStatus.NOT_FOUND);

	}
	

}
