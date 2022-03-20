package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Shirt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private Integer id;
	private String colour;
	private int Cotton;
	private int Woolen;
	private int Silk;
	private int Nylon;
	
	public Shirt() {
		super();
	}
	public Shirt(String colour, int cotton, int woolen, int silk, int nylon,Integer id) {
		super();
		this.id=id;
		this.colour = colour;
		Cotton = cotton;
		Woolen = woolen;
		Silk = silk;
		Nylon = nylon;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public int getCotton() {
		return Cotton;
	}
	public void setCotton(int cotton) {
		Cotton = cotton;
	}
	public int getWoolen() {
		return Woolen;
	}
	public void setWoolen(int woolen) {
		Woolen = woolen;
	}
	public int getSilk() {
		return Silk;
	}
	public void setSilk(int silk) {
		Silk = silk;
	}
	public int getNylon() {
		return Nylon;
	}
	public void setNylon(int nylon) {
		Nylon = nylon;
	}
	

}
