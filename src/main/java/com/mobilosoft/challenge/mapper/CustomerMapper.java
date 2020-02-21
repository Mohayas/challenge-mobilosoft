package com.mobilosoft.challenge.mapper;

import org.springframework.stereotype.Service;

import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.entity.Customer;

@Service
public class CustomerMapper {

	public CustomerDto asCustomerDto(Customer in) {

		CustomerDto out = new CustomerDto();

		out.setId(in.getId());
		out.setFirstName(in.getFirstName());
		out.setLastName(in.getLastName());
		out.setEmail(in.getEmail());
		out.setTel(in.getEmail());

		return out;

	}

	public Customer asCustomer(CustomerDto in, Customer out) {

		out.setId(in.getId());
		out.setFirstName(in.getFirstName());
		out.setLastName(in.getLastName());
		out.setEmail(in.getEmail());
		out.setTel(in.getEmail());

		return out;

	}

}
