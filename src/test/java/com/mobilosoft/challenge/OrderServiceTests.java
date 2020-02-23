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
import com.mobilosoft.challenge.dto.OrderDto;
import com.mobilosoft.challenge.service.OrderService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class OrderServiceTests {

	@Autowired
	OrderService orderService;
	@Value("${welcome.message}")
	private String welcome;
	private static int insertedOrderId;

	private OrderDto initOrder() {
		OrderDto order = new OrderDto();
		order.setName("firstemai@junittest.com");
		order.setType("Type test");

		CustomerDto customerDto = new CustomerDto();
		customerDto.setEmail("firstemai@junittest.com");

		customerDto.setTel("09111111111");
		customerDto.setFirstName("test");
		customerDto.setLastName("junit");
		order.setCustomer(customerDto);

		return order;
	}

	@Test
	@Order(1)
	@Rollback(false)
	public void addOrUpadte() {

		OrderDto order = initOrder();
		order = orderService.addOrUpdate(order);
		insertedOrderId = order.getId();
		Assertions.assertNotEquals(order.getId(), 0);
	}

	@Test
	@Order(2)
	public void findByIdTest() {
		try {

			OrderDto order = orderService.findById(insertedOrderId);
			CustomerDto customerDto = order.getCustomer();
			Assertions.assertNotNull(order);
			Assertions.assertNotNull(customerDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	public void getAll() {

		OrderDto orderDto = initOrder();
		orderDto = orderService.addOrUpdate(orderDto);

		List<OrderDto> ordersFromDb = orderService.getAll();
		int size = ordersFromDb.size();

		Assertions.assertNotEquals(size, 0);

	}

	@Test
	@Order(4)
	@Rollback(false)
	public void delete() {

		orderService.delete(insertedOrderId);
		OrderDto orderDto = orderService.findById(insertedOrderId);

		Assertions.assertNull(orderDto);

	}

}
