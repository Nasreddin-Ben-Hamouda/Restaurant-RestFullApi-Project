package com.rest.restaurant.app.util;

import com.rest.restaurant.app.models.Appetizer;
import com.rest.restaurant.app.models.Dessert;
import com.rest.restaurant.app.models.MainCourse;
import com.rest.restaurant.app.models.Met;

public class MetUtil {

	public static Met createInstance(int type) {
		Met newMet=null;
		switch(type) {
			case 1:newMet=new Appetizer();
			 break;
			case 2:newMet=new MainCourse();
			 break;
			default:newMet=new Dessert();
		}
		return newMet;
	}
	/*public static List<MetDTO> setMetsDTOFromMetList(List<Met> mets) {
		ModelMapper mapper=new ModelMapper();
		List<MetDTO> metsDTO=new ArrayList<>();
		 for(Met met:mets) {
			  MetDTO metDTO=mapper.map(met,MetDTO.class);
			  metDTO.setTypeDependMet(met);
			  metsDTO.add(metDTO);
		  }
		 return metsDTO;
	}*/
}
