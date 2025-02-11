package com.enel.rdd.login.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadWebApplicationHttpSecurityConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.with(AadWebApplicationHttpSecurityConfigurer.aadWebApplication(), Customizer.withDefaults());
			   http.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().requestMatchers("/local")
						.permitAll().requestMatchers("/images/**").permitAll().requestMatchers("/css/**").permitAll().requestMatchers("/js/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/", true))
				.oauth2Login(login -> login.loginPage("/login").permitAll());
		return http.build();
				

	}

}
