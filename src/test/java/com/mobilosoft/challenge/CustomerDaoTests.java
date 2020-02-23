package com.mobilosoft.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.mobilosoft.challenge.dao.CustomerDao;
import com.mobilosoft.challenge.entity.Customer;

@SpringBootTest
@Transactional
@TestMethodOrder(OrderAnnotation.class)
public class CustomerDaoTests {

	@Autowired
	CustomerDao customerDao;
	@Value("${welcome.message}")
	private String welcome;
	private static int insertedCustomerId;

	private Customer initCustomer() {
		Customer customer = new Customer();
		customer.setEmail("firstemai@junittest.com");

		customer.setTel("09111111111");
		customer.setFirstName("test");
		customer.setLastName("junit");
		return customer;
	}

	@Test
	@Order(1)
	@Rollback(false)
	public void addOrUpadte() {

		Customer customer = initCustomer();
		customer = customerDao.addOrUpdate(customer);
		insertedCustomerId = customer.getId();
		Assertions.assertNotEquals(customer.getId(), 0);
	}

	@Test
	@Order(2)
	public void findById() {

		Optional<Customer> customer = customerDao.findById(insertedCustomerId);
		Assertions.assertEquals(true, customer.isPresent());

	}

	@Test
	@Order(3)
	public void getAll() {

		Customer customer = initCustomer();
		customer = customerDao.addOrUpdate(customer);

		List<Customer> customersFromDb = customerDao.getAll();
		int size = customersFromDb.size();

		Assertions.assertNotEquals(size, 0);
	}

	@Test
	@Order(4)
	@Rollback(false)
	public void delete() {

		customerDao.delete(insertedCustomerId);
		Optional<Customer> customer = customerDao.findById(insertedCustomerId);
		Assertions.assertEquals(false, customer.isPresent());
	}

	@Test
	public void testConfigFile() {

		assertEquals(welcome, "Buddytest");

	}

	@Test
	public void testTestConfig() {

		assertEquals(1, 1);

	}

}
