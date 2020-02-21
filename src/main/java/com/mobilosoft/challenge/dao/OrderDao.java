package com.mobilosoft.challenge.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobilosoft.challenge.dto.OrderDto;
import com.mobilosoft.challenge.entity.Order;
import com.mobilosoft.challenge.mapper.OrderMapper;
import com.mobilosoft.challenge.springdata.OrderRepository;

@Repository
public class OrderDao {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderMapper mapper;

	public List<OrderDto> getAll() {

		List<Order> orders = orderRepository.findAll();

		List<OrderDto> orderDtos = orders.stream().map(order -> mapper.asOrderDto(order)).collect(Collectors.toList());

		return orderDtos;
	}

	public OrderDto add(OrderDto orderDto) {

		Order order = new Order();
		order = mapper.asOrder(orderDto, order);
		order = orderRepository.save(order);
		return mapper.asOrderDto(order);
	}

	public boolean delete(int orderid) {
		try {
			Order order = orderRepository.getOne(orderid);
			orderRepository.delete(order);
			return true;
		} catch (Exception e) {
			return false;

		}

	}
}