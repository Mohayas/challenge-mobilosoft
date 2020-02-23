package com.mobilosoft.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilosoft.challenge.dao.OrderDao;
import com.mobilosoft.challenge.dto.OrderDto;
import com.mobilosoft.challenge.entity.Order;
import com.mobilosoft.challenge.mapper.OrderMapper;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	OrderMapper mapper;

	public List<OrderDto> getAll() {
		List<Order> orders = orderDao.getAll();
		List<OrderDto> orderDtos = orders.stream().map(order -> mapper.asOrderDto(order)).collect(Collectors.toList());
		return orderDtos;
	}

	public OrderDto getOne(int orderId) {
		Order order = orderDao.getOne(orderId);
		OrderDto orderDto = mapper.asOrderDto(order);
		return orderDto;
	}

	public OrderDto addOrUpdate(OrderDto orderDto) {
		Order order = new Order();
		order = mapper.asOrder(orderDto, order);
		order = orderDao.addOrUpdate(order);
		orderDto = mapper.asOrderDto(order);
		return orderDto;
	}

	public boolean delete(int orderId) {

		return orderDao.delete(orderId);
	}
}
