package com.mobilosoft.challenge.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.dto.OrderDto;
import com.mobilosoft.challenge.service.CustomerService;
import com.mobilosoft.challenge.service.OrderService;

@Controller
public class OrderController {

	// inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String main(Model model) {

		List<OrderDto> orders = orderService.getAll();
		model.addAttribute("orders", orders);

		List<CustomerDto> customers = customerService.getAll();
		model.addAttribute("customers", customers);

		model.addAttribute("now", Instant.now().getEpochSecond());
		return "order";
	}

	@GetMapping("/order/{orderId}")
	@ResponseBody
	public OrderDto getOne(@PathVariable(name = "orderId") int orderId) {

		System.out.println("order to get : " + orderId);

		return orderService.findById(orderId);

	}

	@PostMapping("/order")
	@ResponseBody
	public OrderDto addOrder(@RequestBody OrderDto orderDto) {

		System.out.println(orderDto);
		return orderService.addOrUpdate(orderDto);

	}

	@PutMapping("/order")
	@ResponseBody
	public OrderDto updateOrder(@RequestBody OrderDto orderDto) {

		System.out.println(orderDto);
		return orderService.addOrUpdate(orderDto);

	}

	@DeleteMapping("/order/{orderId}")
	@ResponseBody
	public boolean delete(@PathVariable(name = "orderId") int orderId) {

		System.out.println("order to delete : " + orderId);

		return orderService.delete(orderId);

	}

}
