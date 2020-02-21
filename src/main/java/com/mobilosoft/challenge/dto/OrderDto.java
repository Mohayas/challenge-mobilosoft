package com.mobilosoft.challenge.dto;

import com.mobilosoft.challenge.entity.Order;

public class OrderDto {

	private int id;

	private String name;

	private String type;

	private CustomerDto customerDto;

	public OrderDto() {

	}

	public OrderDto(Order order) {

		// this.customerDto = orde
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomerDto getCustomer() {
		return customerDto;
	}

	public void setCustomer(CustomerDto customer) {
		this.customerDto = customer;
	}

	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", name=" + name + ", type=" + type + ", customerDto=" + customerDto + "]";
	}

}
