package com.mobilosoft.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilosoft.challenge.dao.CustomerDao;
import com.mobilosoft.challenge.dto.CustomerDto;
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

}
