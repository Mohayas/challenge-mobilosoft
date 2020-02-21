package com.mobilosoft.challenge.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilosoft.challenge.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
