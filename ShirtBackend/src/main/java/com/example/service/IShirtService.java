package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.ShirtDAO;
import com.example.model.Shirt;

public interface IShirtService {
	Shirt select_row(String colour);
	
}
