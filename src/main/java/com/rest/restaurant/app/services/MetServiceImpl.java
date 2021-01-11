package com.rest.restaurant.app.services;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.restaurant.app.dto.PeriodRequest;
import com.rest.restaurant.app.dto.MostBuyableMainCourseResponse;
import com.rest.restaurant.app.models.Met;
import com.rest.restaurant.app.repositories.MetRepository;

@Service
public class MetServiceImpl implements MetService {
	@Autowired
	private MetRepository metRepo;

	@Override
	public List<Met> findAll() {
		return metRepo.findAll();
	}

	@Override
	public Met save(Met met) {
		return metRepo.save(met);
	}

	@Override
	public Met finById(long id) {
		Optional<Met> met=metRepo.findById(id);
		if(met.isPresent())
			return met.get();
		else
			return null;
	}

	@Override
	public Met edit(Met met,int type, long id) {
		Met editedMed=null;
		if(metRepo.updateMet(met.getName(), met.getPrice(), type, id)>0) {
			editedMed=this.finById(id);
		}
		return editedMed;
	}

	@Override
	public Boolean delete(long id) {
		Met met=this.finById(id);
		if(met!=null) {
			metRepo.delete(met);
			return true;
		}
		return false;
	}

	@Override
	public MostBuyableMainCourseResponse getMostBuyableMainCourse(PeriodRequest requset) {
		Pageable getFirstMet = PageRequest.of(0, 1);
		List<MostBuyableMainCourseResponse> mets=metRepo.mostBuyableMainCourses(requset.getFrom().atStartOfDay(),requset.getTo().atTime(LocalTime.MAX),getFirstMet);
		if(mets.size()>0)
			return mets.get(0);
		else
			return null;
	}


}
