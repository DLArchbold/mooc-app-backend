package com.in28minutes.rest.webservices.restfulwebservices.security;




import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfiguration {
//
//	protected void configure(HttpSecurity http) throws Exception {
//
////		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
//		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////		jwtAuthenticationConverter.
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter()); // delegate to custom converter
//		
//		http.authorizeRequests()
//		.requestMatchers(HttpMethod.GET, "/users/status/check")
//		.hasRole("developer")
//		
////		.hasAuthority("ROLE_developer")
////		.hasAuthority("SCOPE_profile")
//		
//		.anyRequest()
//				.authenticated().and().oauth2ResourceServer().jwt()
//	.jwtAuthenticationConverter(jwtAuthenticationConverter);
//		
//		
//        
//     
//		
//		
//	}
//
//}




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
public class WebSecurityConfig {
//   private final UserDetailsService userDetailsService;

//   private final AuthEntryPointJwt unauthorizedHandler;
//
//   private final AuthTokenFilter authenticationJwtTokenFilter;

   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//       http.cors().and().csrf().disable()
//               .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//               .authorizeRequests()
//               .antMatchers(UrlMapping.AUTH + UrlMapping.SIGN_UP).permitAll()
//               .antMatchers(UrlMapping.AUTH + UrlMapping.LOGIN).permitAll()
//               .antMatchers(UrlMapping.VALIDATE_JWT).permitAll()
//               .antMatchers("/api/test/**").permitAll()
//               .anyRequest().authenticated();
//
//       http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//       
//       
//       
//       
//       return http.build();
       
       
	   
	   
	   
	   
	   
	   
	   
	   
	   
       JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
//		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.
       jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter()); // delegate to custom converter
		
       
       //For all GET & POST requests to /application_user, allow them without authorization
		http.authorizeRequests()
		.requestMatchers("/application_user/**").permitAll()
		.requestMatchers(HttpMethod.POST, "/**").permitAll().and().httpBasic().and().csrf().disable();
//		.requestMatchers(HttpMethod.POST, "/application_user/get/usingEmail/**").permitAll().and().httpBasic().and().csrf().disable();
//		.requestMatchers(HttpMethod.GET, "/enrolled/get/all").authenticated();

		
		//For all GET requests to /user_follow, allow them only if have authorization
//		http.authorizeRequests()
//		.requestMatchers("user_follow/**").authenticated();
		
		http.authorizeRequests()
		.requestMatchers("/user_follow/**").permitAll();
		
		
		
		//For other routes, only allow if have proper authorization and roles
		http.authorizeRequests()
		.anyRequest()
				.authenticated().and().oauth2ResourceServer().jwt()
	.jwtAuthenticationConverter(jwtAuthenticationConverter);
		

		
		
		//DOCUMENTATION:
		//When testing security configuration by sending requests through POSTMAN/curl for POST requests
		//even if using .permitAll(), need to add .and().httpBasic().and().csrf().disable();
		//for it to work where the same is not needed for GET requests.
//		.requestMatchers(HttpMethod.POST, "/**").permitAll().and().httpBasic().and().csrf().disable();
		
		//allow all requests to application_user resource
		
		//Require authentication to all other resources
		
		
		
		return http.build();
   }

//   @Bean
//   public WebMvcConfigurer corsConfigurer() {
//       return new WebMvcConfigurer() {
//           @Override
//           public void addCorsMappings(CorsRegistry registry) {
//               registry.addMapping("/**")
//                       .allowedMethods("*");
//           }
//       };
//   }
}














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
