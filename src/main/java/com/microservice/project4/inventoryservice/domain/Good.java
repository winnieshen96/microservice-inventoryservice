package com.microservice.project4.inventoryservice.domain;

public class Good {

	private String name;
	private int price;
	
	public Good(String name, int price) {
		super();
		this.name = name.toLowerCase(); //to ignore case
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toLowerCase(); //to ignore case
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
