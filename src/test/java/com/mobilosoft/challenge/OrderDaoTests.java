package com.mobilosoft.challenge;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.mobilosoft.challenge.dao.OrderDao;
import com.mobilosoft.challenge.entity.Customer;
import com.mobilosoft.challenge.entity.Order;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class OrderDaoTests {

	@Autowired
	OrderDao orderDao;
	@Value("${welcome.message}")
	private String welcome;
	private static int insertedOrderId;

	private Order initOrder() {
		Order order = new Order();
		order.setName("order Name");
		order.setType("Type test");

		Customer customer = new Customer();
		customer.setEmail("firstemai@junittest.com");
		customer.setTel("09111111111");
		customer.setFirstName("test");
		customer.setLastName("junit");

		order.setCustomer(customer);

		return order;
	}

	@Test
	@org.junit.jupiter.api.Order(1)
	@Rollback(false)
	public void addOrUpadte() {

		Order order = initOrder();
		order = orderDao.addOrUpdate(order);
		insertedOrderId = order.getId();
		Assertions.assertNotEquals(order.getId(), 0);
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	public void findById() {

		Customer customer = null;
		Optional<Order> order = orderDao.findById(insertedOrderId);
		Assertions.assertEquals(true, order.isPresent());
		if (order.isPresent()) {
			customer = order.get().getCustomer();
		}
		Assertions.assertNotNull(customer);
	}

	@Test
	@org.junit.jupiter.api.Order(3)
	public void getAll() {

		Order order = initOrder();
		order = orderDao.addOrUpdate(order);

		List<Order> ordersFromDb = orderDao.getAll();
		int size = ordersFromDb.size();

		Assertions.assertNotEquals(size, 0);

	}

	@Test
	@org.junit.jupiter.api.Order(4)
	@Rollback(false)
	public void delete() {

		orderDao.delete(insertedOrderId);
		Optional<Order> order = orderDao.findById(insertedOrderId);
		Assertions.assertEquals(false, order.isPresent());

	}

}
