package br.com.queue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirecionaController {
	@RequestMapping("/")
	public String home() {
		return "dashboard";
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/login");
	}
}
