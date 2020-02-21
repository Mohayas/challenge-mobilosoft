package com.mobilosoft.challenge.dto;

import java.util.List;

public class CustomerDto {

	private int id;

	private String email;

	private String firstName;

	private String lastName;

	private String tel;

	private List<OrderDto> orders;

	public CustomerDto() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", tel=" + tel + "]";
	}

}
