package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.dao.ShirtDAO;
import com.example.model.Shirt;

@Controller
public class ShirtInitialLoadPageController {

	@Autowired
	ShirtDAO Shirtdao;
	public void insert_data(Shirt shirt) {
		Shirtdao.save(shirt);	// TODO Auto-generated method stub
		
	}

}
