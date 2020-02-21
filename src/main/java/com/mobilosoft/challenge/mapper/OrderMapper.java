package com.mobilosoft.challenge.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.dto.OrderDto;
import com.mobilosoft.challenge.entity.Customer;
import com.mobilosoft.challenge.entity.Order;

@Service
public class OrderMapper {

	@Autowired
	CustomerMapper customerMapper;

	public OrderDto asOrderDto(Order in) {

		OrderDto out = new OrderDto();
		out.setId(in.getId());
		out.setName(in.getName());
		out.setType(in.getType());

		CustomerDto customerDto = customerMapper.asCustomerDto(in.getCustomer());
		out.setCustomer(customerDto);

		return out;
	}

	public Order asOrder(OrderDto in, Order out) {

		out.setId(in.getId());
		out.setName(in.getName());
		out.setType(in.getType());
		Customer customer = new Customer();
		customer.setId(in.getCustomer().getId());
		out.setCustomer(customer);

		return out;
	}

}
