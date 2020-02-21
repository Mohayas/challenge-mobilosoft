package com.mobilosoft.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilosoft.challenge.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
