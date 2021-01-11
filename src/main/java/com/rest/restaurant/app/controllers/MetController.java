package com.rest.restaurant.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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

import com.rest.restaurant.app.dto.MetDTO;
import com.rest.restaurant.app.dto.PeriodRequest;
import com.rest.restaurant.app.dto.MostBuyableMainCourseResponse;
import com.rest.restaurant.app.models.Met;
import com.rest.restaurant.app.services.MetService;
import com.rest.restaurant.app.util.MetUtil;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/met")
@AllArgsConstructor
public class MetController {
	private MetService metService;
	private ModelMapper mapper;
	
	@GetMapping("/all")
	public List<MetDTO> getAllMets(){
		
		return metService.findAll()
						   .stream()
						   .map(met->{
								   MetDTO metDTO=mapper.map(met, MetDTO.class);
								   metDTO.setTypeDependMet(met);
								   return metDTO;
							   })
						   .collect(Collectors.toList());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getMetById(@PathVariable long id) {
		Met met=metService.finById(id);
		MetDTO metDTO=null;
		if(met!=null) {
			metDTO=mapper.map(met, MetDTO.class);
			metDTO.setTypeDependMet(met);
			return new ResponseEntity<MetDTO>(metDTO,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Met not found",HttpStatus.NOT_FOUND);

	}
	@PostMapping("/create")
	public MetDTO createMet(@Valid @RequestBody MetDTO met) {
		Met newMet=MetUtil.createInstance(met.getType());
		BeanUtils.copyProperties(met, newMet);
		met=mapper.map(metService.save(newMet), MetDTO.class);
		met.setTypeDependMet(newMet);
		return met;
	}
	@PutMapping("/{id}/edit")
	public ResponseEntity<?> editMet(@Valid @RequestBody MetDTO metDTO,@PathVariable long id) {
		Met met=MetUtil.createInstance(metDTO.getType());
		BeanUtils.copyProperties(metDTO, met);
		met=metService.edit(met,metDTO.getType(),id);	
		if(met!=null) {
			metDTO=mapper.map(met, MetDTO.class);
			metDTO.setTypeDependMet(met);
			return new ResponseEntity<MetDTO>(metDTO,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Met not found",HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteMet(@PathVariable long id) {
		
		if(metService.delete(id))
			return new ResponseEntity<String>("successfully operation",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Met not found",HttpStatus.NOT_FOUND);

	}
	
	@PostMapping("/mostMet")
	public MostBuyableMainCourseResponse getMostBuyableMainCourse(@RequestBody PeriodRequest request){
		return metService.getMostBuyableMainCourse(request);
	}
	
}
