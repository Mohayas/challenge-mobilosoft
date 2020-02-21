package com.mobilosoft.challenge.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobilosoft.challenge.entity.Customer;
import com.mobilosoft.challenge.springdata.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAll() {

		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

}
