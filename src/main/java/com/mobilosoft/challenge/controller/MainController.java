package com.mobilosoft.challenge.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	// inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("message", message);

		return "order"; // view
	}

}
