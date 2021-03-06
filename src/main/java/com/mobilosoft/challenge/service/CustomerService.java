package com.mobilosoft.challenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilosoft.challenge.common.ApiResponses;
import com.mobilosoft.challenge.dao.CustomerDao;
import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.entity.Customer;
import com.mobilosoft.challenge.mapper.CustomerMapper;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerMapper mapper;

	public List<CustomerDto> getAll() {

		List<CustomerDto> customers = customerDao.getAll().stream().map(customer -> mapper.asCustomerDto(customer))
				.collect(Collectors.toList());
		return customers;
	}

	public CustomerDto findById(int customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if (!customer.isPresent())
			return null;
		CustomerDto customerDto = mapper.asCustomerDto(customer.get());
		return customerDto;
	}

	public CustomerDto addOrUpdate(CustomerDto customerDto) {

		Customer customer = new Customer();
		customer = mapper.asCustomer(customerDto, customer);
		customer = customerDao.addOrUpdate(customer);
		customerDto = mapper.asCustomerDto(customer);
		return customerDto;
	}

	public int delete(int customerId, boolean forceDelete) {

		Optional<Customer> customerOpt = customerDao.findById(customerId);

		if (!customerOpt.isPresent())
			return ApiResponses.DELETE_CUSTOMER_NOT_FOUD;

		Customer customer = customerOpt.get();
		if (customer.getOrders().size() > 0 && !forceDelete)
			return ApiResponses.DELETE_CUSTOMER_HAS_ORDERS;

		return customerDao.delete(customerId);
	}
}
