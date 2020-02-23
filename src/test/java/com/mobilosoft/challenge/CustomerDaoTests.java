package com.mobilosoft.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobilosoft.challenge.dao.CustomerDao;
import com.mobilosoft.challenge.entity.Customer;

@SpringBootTest
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerDaoTests {

	@Autowired
	CustomerDao customerDao;
	@Value("${welcome.message}")
	private String welcome;
	private static int insrtedCustomerId;

	private Customer initCustomer() {
		Customer customer = new Customer();
		customer.setEmail("firstemai@junittest.com");

		customer.setTel("09111111111");
		customer.setFirstName("test");
		customer.setLastName("junit");
		return customer;
	}

	@Test
	public void addOrUpadte() {

		Customer customer = initCustomer();
		customer = customerDao.addOrUpdate(customer);
		insrtedCustomerId = customer.getId();
		Assertions.assertNotEquals(customer.getId(), 0);
	}

	@Test
	public void getOne() {
		try {

			Customer customer = customerDao.getOne(insrtedCustomerId);
			Assertions.assertNotNull(customer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAll() {

		Customer customer = initCustomer();
		customer = customerDao.addOrUpdate(customer);

		List<Customer> customersFromDb = customerDao.getAll();
		int size = customersFromDb.size();

		Assertions.assertNotEquals(size, 0);

	}

	@Test
	public void delete() {

		customerDao.delete(insrtedCustomerId);

		List<Customer> customersFromDb = customerDao.getAll();
		int size = customersFromDb.size();

		Assertions.assertEquals(size, 0);

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
