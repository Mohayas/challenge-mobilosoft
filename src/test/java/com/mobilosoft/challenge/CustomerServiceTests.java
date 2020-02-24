package com.mobilosoft.challenge;

import java.util.List;

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

import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.service.CustomerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class CustomerServiceTests {

	@Autowired
	CustomerService customerService;
	@Value("${welcome.message}")
	private String welcome;
	private static int insertedCustomerId;

	private CustomerDto initCustomer() {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setEmail("firstemai@junittest.com");

		customerDto.setTel("09111111111");
		customerDto.setFirstName("test");
		customerDto.setLastName("junit");
		return customerDto;
	}

	@Test
	@Order(1)
	@Rollback(false)
	public void addOrUpadte() {

		CustomerDto customer = initCustomer();
		customer = customerService.addOrUpdate(customer);
		insertedCustomerId = customer.getId();
		Assertions.assertNotEquals(customer.getId(), 0);
	}

	@Test
	@Order(2)
	public void getOne() {
		try {

			CustomerDto customer = customerService.findById(insertedCustomerId);
			Assertions.assertNotNull(customer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	public void getAll() {

		CustomerDto customer = initCustomer();
		customer = customerService.addOrUpdate(customer);

		List<CustomerDto> customersFromDb = customerService.getAll();
		int size = customersFromDb.size();

		Assertions.assertNotEquals(size, 0);

	}

	@Test
	@Order(4)
	@Rollback(false)
	public void delete() {

		customerService.delete(insertedCustomerId, true);
		CustomerDto customerDto = customerService.findById(insertedCustomerId);
		Assertions.assertNull(customerDto);

	}

}
