package com.example.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Shirt;

@Repository
public interface ShirtDAO extends CrudRepository<Shirt, Integer> {
	
	@Query("from Shirt s where s.colour is ?1")
	Shirt select_row(String colour);
	
}
