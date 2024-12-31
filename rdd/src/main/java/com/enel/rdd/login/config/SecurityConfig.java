package com.enel.rdd.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadWebApplicationHttpSecurityConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.apply(AadWebApplicationHttpSecurityConfigurer.aadWebApplication()).and()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().requestMatchers("/local")
						.permitAll().requestMatchers("/images/**").permitAll().requestMatchers("/css/**").permitAll().requestMatchers("/js/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(form -> form.defaultSuccessUrl("/", true)).build();

	}

}
