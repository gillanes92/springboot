package com.enel.rdd.login.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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
	
	@GetMapping("/login")
	public String login(Model model,@AuthenticationPrincipal OidcUser principal) {
		
		return "redirect:/";
	}
	
	@GetMapping("/loginRDD")
	public String loginRDD(Model model,@AuthenticationPrincipal OidcUser principal) {
		
		if(principal!=null) {
			System.out.println("principal: "+principal);
			
			model.addAttribute("username", principal.getPreferredUsername());
			
		}else {
			SecurityContext a = SecurityContextHolder.getContext();
			System.out.println("NAME: "+a.getAuthentication().getName());
			System.out.println("DETAILS: "+a.getAuthentication().getDetails());
			System.out.println("PRINCIPAL: "+a.getAuthentication().getPrincipal());
			System.out.println("AUTHORITIES: "+a.getAuthentication().getAuthorities());		
			
			for (GrantedAuthority auth : a.getAuthentication().getAuthorities()) {
	            if ("ROLE_dashboard".equals(auth.getAuthority()))
	            	return  "redirect:/dashboard";
	 	   }
			
		}	
		return "login/loginRDD";
	}
	
}
