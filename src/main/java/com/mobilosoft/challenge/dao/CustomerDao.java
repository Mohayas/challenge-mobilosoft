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

	public Customer getOne(int customerId) {

		Customer customer = customerRepository.getOne(customerId);

		return customer;

	}

	public Customer addOrUpdate(Customer customer) {

		customer = customerRepository.save(customer);
		return customer;
	}

	public boolean delete(int customerId) {

		try {
			Customer customer = customerRepository.getOne(customerId);
			customerRepository.delete(customer);
			return true;
		} catch (Exception e) {
			return false;

		}
	}
}
