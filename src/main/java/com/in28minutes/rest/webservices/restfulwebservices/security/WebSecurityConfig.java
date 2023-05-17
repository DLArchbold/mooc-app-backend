

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;



@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration {

	protected void configure(HttpSecurity http) throws Exception {

//		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter()); // delegate to custom converter
		
		http.authorizeRequests()
		.requestMatchers(HttpMethod.GET, "/users/status/check")
		.hasRole("developer")
//		.hasAuthority("ROLE_developer")
//		.hasAuthority("SCOPE_profile")
		.anyRequest()
				.authenticated().and().oauth2ResourceServer().jwt()
	.jwtAuthenticationConverter(jwtAuthenticationConverter);
	}

}


























/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.in28minutes.rest.webservices.restfulwebservices.security;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
//
//
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
////		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
//		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////		jwtAuthenticationConverter.
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter()); // delegate to custom converter
//		
//		http.authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/users/status/check")
//		.hasRole("developer")
////		.hasAuthority("ROLE_developer")
////		.hasAuthority("SCOPE_profile")
//		.anyRequest()
//				.authenticated().and().oauth2ResourceServer().jwt()
//	.jwtAuthenticationConverter(jwtAuthenticationConverter);
//	}
//
//}
