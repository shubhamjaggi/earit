package com.github.shubhamjaggi.earit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/earit/ui/")
public class EarItUiController {

	@GetMapping(value = "/index")
	public String index() {
		return "index";
	}
}