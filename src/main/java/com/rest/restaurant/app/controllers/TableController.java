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

import com.rest.restaurant.app.dto.TableDTO;
import com.rest.restaurant.app.models.TableEntity;
import com.rest.restaurant.app.services.TableService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/table")
@AllArgsConstructor
public class TableController {
	private TableService tableService;
	private ModelMapper mapper;
	
	@GetMapping("/all")
	public List<TableDTO> getAllTableType(){
		
		return tableService.findAll()
						   .stream()
						   .map(table->mapper.map(table, TableDTO.class))
						   .collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTableById(@PathVariable long id) {
		TableEntity table=tableService.finById(id);
		if(table!=null)
			return new ResponseEntity<TableDTO>(mapper.map(table, TableDTO.class),HttpStatus.OK);
		else
			return new ResponseEntity<String>("table not found",HttpStatus.NOT_FOUND);

	}
	
	@PostMapping("/create")
	public TableDTO createTable(@Valid @RequestBody TableDTO table) {
		TableEntity newTable=tableService.save(mapper.map(table, TableEntity.class));	
		return mapper.map(newTable, TableDTO.class);
	}
	
	@PutMapping("/{number}/edit")
	public ResponseEntity<?> editTable(@Valid @RequestBody TableDTO tableDTO,@PathVariable long number) {
		TableEntity table=tableService.edit(mapper.map(tableDTO,TableEntity.class), number);
		if(table!=null) 
			return new ResponseEntity<TableDTO>(mapper.map(table, TableDTO.class),HttpStatus.OK);
		else
			return new ResponseEntity<String>("table not found",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{number}/delete")
	public ResponseEntity<?> deleteTable(@PathVariable long number) {
		
		if(tableService.delete(number))
			return new ResponseEntity<String>("successfully operation",HttpStatus.OK);
		else
			return new ResponseEntity<String>("table not found",HttpStatus.NOT_FOUND);

	}
}
