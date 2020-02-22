package com.mobilosoft.challenge.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilosoft.challenge.dao.OrderDao;
import com.mobilosoft.challenge.dto.OrderDto;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderDao orderDao;

	public List<OrderDto> getAll() {

		return orderDao.getAll();
	}

	public OrderDto getOne(int orderId) {

		return orderDao.getOne(orderId);
	}

	public OrderDto add(OrderDto orderDto) {

		return orderDao.add(orderDto);
	}

	public OrderDto update(OrderDto orderDto) {

		return orderDao.update(orderDto);
	}

	public boolean delete(int orderId) {

		return orderDao.delete(orderId);
	}
}
