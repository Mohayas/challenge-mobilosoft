package com.mobilosoft.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilosoft.challenge.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
