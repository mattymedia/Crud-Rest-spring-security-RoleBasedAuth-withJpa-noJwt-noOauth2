package co.com.jwtrolebasedauthjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.com.jwtrolebasedauthjpa.service.JpaUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JpaUserDetailsService jpaUserDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.authorizeHttpRequests(
						request -> 
						request.antMatchers("/h2-console/**", "/books/home", 
										"/books/show", "/books/show/**", "/users/show",
										"/users/show/**").permitAll()
						.anyRequest().authenticated()
				)
				.userDetailsService(jpaUserDetailsService)
				.headers(headersParam -> headersParam.frameOptions().sameOrigin())
				.httpBasic(Customizer.withDefaults())
				.build();
		       
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
