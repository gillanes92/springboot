package com.andex.clinica_veterinaria_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

@Controller
public class MascotaController {
	
	@GetMapping({"","/"})
	public String listarAll(Model model) {
		
				
		
		return "inicio";
	}
	
}
