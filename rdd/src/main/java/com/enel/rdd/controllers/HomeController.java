package com.enel.rdd.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping({"","/"})
	public String home(Model model) {
		
		SecurityContext a = SecurityContextHolder.getContext();
		
		if(a.getAuthentication().isAuthenticated()) {
			
			System.out.println(a.getAuthentication().getPrincipal());
			
			model.addAttribute("username", a.getAuthentication());			
			
		}
		
		return "index";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
