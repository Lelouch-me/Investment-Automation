package com.analyzer.automation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll())
			.csrf().disable();

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 =
			 User.withDefaultPasswordEncoder()
				.username("fahim")
				.password("fahim@9654#")
				.roles("USER")
				.build();
		
		UserDetails user2 =
				 User.withDefaultPasswordEncoder()
					.username("sumi")
					.password("sumi@7429&")
					.roles("USER")
					.build();
		
		UserDetails user3 =
				 User.withDefaultPasswordEncoder()
					.username("imran")
					.password("imran@5871$")
					.roles("USER")
					.build();
		
		UserDetails user4 =
				 User.withDefaultPasswordEncoder()
					.username("sivlee")
					.password("sivlee#6852@")
					.roles("USER")
					.build();
		
		UserDetails user5 =
				 User.withDefaultPasswordEncoder()
					.username("mehrub")
					.password("mehrub%4869#")
					.roles("USER")
					.build();
				
		UserDetails user6 =
				 User.withDefaultPasswordEncoder()
					.username("navin")
					.password("navin$2983&")
					.roles("USER")
					.build();
		
		UserDetails user7 =
				 User.withDefaultPasswordEncoder()
					.username("admin")
					.password("admin123")
					.roles("USER")
					.build();
		
		UserDetails user8 =
				 User.withDefaultPasswordEncoder()
					.username("mehedi")
					.password("mehedi@369")
					.roles("USER")
					.build();
		

		return new InMemoryUserDetailsManager(user1,user2,user3,user4,user5,user6,user7,user8);
	}
}
