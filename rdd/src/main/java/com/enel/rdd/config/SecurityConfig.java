package com.enel.rdd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadWebApplicationHttpSecurityConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http.apply(AadWebApplicationHttpSecurityConfigurer.aadWebApplication())
				.and()
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/","/local").permitAll()
						.anyRequest().authenticated()
					)
					//.formLogin(form -> form.loginPage("/login").permitAll(false))
					//.logout(config -> config.logoutSuccessUrl("/"))
					.build();
		
	}
	
}
