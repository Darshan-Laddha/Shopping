package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ShirtDAO;
import com.example.model.Shirt;
import com.example.service.IShirtService;

@RestController
@CrossOrigin(origins = "*")
public class ShirtController {
	
	@Autowired
	ShirtDAO ShirtDao;
	
	@Autowired 
	IShirtService ishirtservice;
	
	@Autowired
	ShirtInitialLoadPageController shirtInitialLoadPageController;
	
	@RequestMapping(value="/sql_adder",method=RequestMethod.POST)
	public void sql_adder(@RequestBody String map_in_string_format,Shirt shirt) {
		
		System.out.println(map_in_string_format+" input taken");
		int length=map_in_string_format.length()-2;
		map_in_string_format=map_in_string_format.substring(2,length);
		Map<String,String>all_colours=new HashMap();
		String each_color_details[]=map_in_string_format.split(",");
		for(String each_color_detail:each_color_details)
		all_colours.put(each_color_detail.substring(0,each_color_detail.indexOf(':')), each_color_detail.substring(each_color_detail.indexOf(':')+1));
		
		for(String color:all_colours.keySet()) {
			//shirt.setId(null);
			shirt.setColour(color);
			String material_detail_of_colour=all_colours.get(color);
			int cotton_start=material_detail_of_colour.indexOf("Cotton Stock=")+"Cotton Stock=".length();
			int cotton_end=material_detail_of_colour.indexOf("Woolen Stock=")-1;
			
			int woolen_start=material_detail_of_colour.indexOf("Woolen Stock=")+"Woolen Stock=".length();
			int woolen_end=material_detail_of_colour.indexOf("Silk Stock=")-1;
			
			int silk_start=material_detail_of_colour.indexOf("Silk Stock=")+"Silk Stock=".length();
			int silk_end=material_detail_of_colour.indexOf("Nylon Stock=")-1;
			
			int nylon_start=material_detail_of_colour.indexOf("Nylon Stock=")+"Nylon Stock=".length();
			int nylon_end=material_detail_of_colour.length();
			
			shirt.setCotton(Integer.parseInt(material_detail_of_colour.substring(cotton_start,cotton_end)));
			shirt.setWoolen(Integer.parseInt(material_detail_of_colour.substring(woolen_start,woolen_end)));
			shirt.setSilk(Integer.parseInt(material_detail_of_colour.substring(silk_start,silk_end)));
			shirt.setNylon(Integer.parseInt(material_detail_of_colour.substring(nylon_start,nylon_end)));
			
			if(ShirtDao.select_row(shirt.getColour())==null)
			ShirtDao.save(shirt);
			else {
				ShirtDao.delete(ShirtDao.select_row(shirt.getColour()));
				ShirtDao.save(shirt);
			}
		}
	}
	@RequestMapping(value="/row_for_ChildNode_adder",method=RequestMethod.POST)
	public ResponseEntity row_for_ChildNode_adder(@RequestBody String color) {
		List<String> cloth_details=new ArrayList();
		System.out.println(color);
		Shirt selected_shirt=ShirtDao.select_row(color);
		System.out.println(selected_shirt);
		//cloth_details will contain th entire material list whenever a new color is selected
		if(selected_shirt!=null) {
			cloth_details.add("Cotton Stock:"+selected_shirt.getCotton()+"");
			cloth_details.add("Woolen Stock:"+selected_shirt.getWoolen()+"");
			cloth_details.add("Silk Stock:"+selected_shirt.getSilk()+"");
			cloth_details.add("Nylon Stock:"+selected_shirt.getNylon()+"");
		}
		System.out.print(cloth_details);
		return ResponseEntity.ok().body(cloth_details);
		
	}
	Shirt shirt=new Shirt();
	@RequestMapping(value="/sql_connector",method=RequestMethod.POST)
	public void color_specific_row_selector(@RequestBody String color_data) {
		System.out.print(color_data);
		StringBuilder cloth_data=new StringBuilder();
		String materials[]=color_data.split(";");
			for(String material:materials) {
				String each_material[]=material.split(":");
				if(each_material[0].equals("Cotton Stock"))
					shirt.setCotton(Integer.parseInt(each_material[1]));
				else if(each_material[0].equals("Woolen Stock"))
					shirt.setWoolen(Integer.parseInt(each_material[1]));
				else if(each_material[0].equals("Silk Stock"))
					shirt.setSilk(Integer.parseInt(each_material[1]));
				else if(each_material[0].equals("Nylon Stock"))
					shirt.setNylon(Integer.parseInt(each_material[1]));
				else
					shirt.setColour(each_material[0]);
			}
			Shirt selected_shirt=ShirtDao.select_row(shirt.getColour());
			if(selected_shirt==null) {
				shirt.setId(0);
				ShirtDao.save(shirt);
			}
			else {
				shirt.setId(0);
				Shirt shirt_color_to_delete=ShirtDao.select_row(shirt.getColour());
				ShirtDao.delete(shirt_color_to_delete);
				ShirtDao.save(shirt);
			}
			/*selected_shirt=ShirtDao.select_row(shirt.getColour());
			cloth_data.append("Color:"+selected_shirt.getColour()+";");
			cloth_data.append("Cotton Stock:"+selected_shirt.getCotton()+";");
			cloth_data.append("Woolen Stock:"+selected_shirt.getWoolen()+";");
			cloth_data.append("Silk Stock:"+selected_shirt.getSilk()+";");
			cloth_data.append("Nylon Stock:"+selected_shirt.getNylon());
			System.out.println(cloth_data);
			
			return ResponseEntity.ok().body(cloth_data);*/
			
	}

}
