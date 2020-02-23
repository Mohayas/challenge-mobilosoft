package com.mobilosoft.challenge.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobilosoft.challenge.common.ApiResponses;
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

	public Optional<Customer> findById(int customerId) {

		Optional<Customer> customer = customerRepository.findById(customerId);
		return customer;
	}

	public Customer addOrUpdate(Customer customer) {

		customer = customerRepository.save(customer);
		return customer;
	}

	public int delete(int customerId) {

		Customer customer = customerRepository.getOne(customerId);
		customerRepository.delete(customer);
		return ApiResponses.DELETE_CUSTOMER_SUCCESS;

	}
}
