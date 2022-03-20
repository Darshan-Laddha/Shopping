package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ShirtDAO;
import com.example.model.Shirt;

@Service
public class ShirtService implements IShirtService {
	
	@Autowired
	private ShirtDAO Shirtdao;
	@Override
	public Shirt select_row(String colour) {
		Shirt shirt=Shirtdao.select_row(colour);
		return shirt;// TODO Auto-generated method stub
	}
	
//	public getMaterialStock() {
//		
//	}

}
