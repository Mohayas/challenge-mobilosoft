package com.mobilosoft.challenge.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.entity.Customer;
import com.mobilosoft.challenge.mapper.CustomerMapper;
import com.mobilosoft.challenge.springdata.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	CustomerMapper mapper;

	public List<Customer> getAll() {

		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	public CustomerDto getOne(int customerId) {

		Customer customer = customerRepository.getOne(customerId);
		CustomerDto customerDto = mapper.asCustomerDto(customer);
		return customerDto;

	}

	public CustomerDto addOrUpdate(CustomerDto customerDto) {

		Customer customer = new Customer();
		customer = mapper.asCustomer(customerDto, customer);
		customer = customerRepository.save(customer);
		customerDto = mapper.asCustomerDto(customer);
		return customerDto;
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
