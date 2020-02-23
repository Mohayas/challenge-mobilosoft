package com.mobilosoft.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobilosoft.challenge.dao.CustomerDao;
import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.entity.Customer;

@SpringBootTest
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerDaoTests {

	@Autowired
	CustomerDao customerDao;
	@Value("${welcome.message}")
	private String welcome;

	@Test
	public void testSave() {
		CustomerDto customer = new CustomerDto();
		customer.setEmail("emaaail");

		customer.setTel("0998888888");
		customer.setFirstName("mooooha");
		customer.setLastName("laste naaaaame");
		customerDao.addOrUpdate(customer);

		List<Customer> customersFromDb = customerDao.getAll();
		int size = Integer.valueOf(customersFromDb.size());
		assertEquals(size, 12);

	}

	@Test
	public void testConfigFile() {

		assertEquals(welcome, "Buddy");

	}

	@Test
	public void testTestConfig() {

		assertEquals(1, 1);

	}
}
