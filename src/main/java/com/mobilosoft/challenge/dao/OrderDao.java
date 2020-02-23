package com.mobilosoft.challenge.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobilosoft.challenge.entity.Order;
import com.mobilosoft.challenge.springdata.OrderRepository;

@Repository
public class OrderDao {

	@Autowired
	OrderRepository orderRepository;

	public List<Order> getAll() {

		List<Order> orders = orderRepository.findAll();
		return orders;
	}

	public Optional<Order> findById(int orderId) {

		Optional<Order> order = orderRepository.findById(orderId);
		return order;

	}

	public Order addOrUpdate(Order order) {
		order = orderRepository.save(order);
		return order;
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