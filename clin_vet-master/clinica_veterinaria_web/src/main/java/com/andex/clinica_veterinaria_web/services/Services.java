package com.andex.clinica_veterinaria_web.services;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class Services {
	
	private final RestClient restClient;
	
	public Services() {
		this.restClient = RestClient.builder().build();
	}
	
	public List<String> MascotasAll() {
		
		return this.restClient.get()
				.uri("http://localhost:8081/")
				.retrieve()
				.body(new ParameterizedTypeReference<List<String>>() {});
	}
	
}