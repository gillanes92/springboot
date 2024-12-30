package com.enel.rdd.login.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
	
	@GetMapping({"","/"})
	public String home(Model model) {
		
		SecurityContext a = SecurityContextHolder.getContext();
		
		if(!a.getAuthentication().getPrincipal().equals("anonymousUser")) {
			
			return "redirect:/loginRDD";		
			
		}
		
		return "index";
	}
	
	@GetMapping("/loginRDD")
	public String login(Model model,@AuthenticationPrincipal OidcUser principal) {
		System.out.println(principal.getPreferredUsername());
		model.addAttribute("username", principal.getPreferredUsername());		
		return "login/loginRDD";
	}
	
}
