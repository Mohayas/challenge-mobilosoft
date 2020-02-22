package com.mobilosoft.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobilosoft.challenge.dto.CustomerDto;
import com.mobilosoft.challenge.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer/all")
	public String main(Model model) {

		List<CustomerDto> customers = customerService.getAll();
		model.addAttribute("customers", customers);

		return "customers";
	}

	@GetMapping("/customer/{customerId}")
	public String getOne(@PathVariable(name = "customerId") int customerId, Model model) {

		System.out.println("customer to get : " + customerId);

		CustomerDto customerDto = customerService.getOne(customerId);
		model.addAttribute("customer", customerDto);

		return "add-customer";
	}

	@GetMapping("/customer/add")
	public String takeToAddCustomer() {
		return "add-customer";
	}

	@PostMapping("/customer")
	@ResponseBody
	public CustomerDto save(@RequestBody CustomerDto customerDto) {

		System.out.println(customerDto);
		customerDto = customerService.addOrUpdate(customerDto);
		return customerDto;

	}

	@DeleteMapping("/customer/{customerId}")
	@ResponseBody
	public boolean delete(@PathVariable(name = "customerId") int customerId) {

		System.out.println("customer to delete : " + customerId);
		return true;
		// return customerService.delete(customerId);

	}

}
