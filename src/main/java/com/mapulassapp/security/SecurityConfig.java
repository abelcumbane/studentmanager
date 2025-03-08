package com.mapulassapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mapulassapp.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends VaadinWebSecurity {
	 
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable());

		super.configure(http);
		setLoginView(http,LoginView.class);	
	}
	
	
	
	@Override
	protected void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers("/images/**", "/login", "/signup");
		super.configure(web);
	}
	
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}
	
	
	/*
	protected void configure(HttpSecurity http) throws Exception {
		
        // Configuração antiga (Spring Security 5.x)
        // http.authorizeRequests()
        //     .antMatchers("/images/**", "/login", "/signup").permitAll()
        //     .anyRequest().authenticated();
        
        super.configure(http);

        // Configuração nova (Spring Security 6.x)
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/images/**", "/login", "/signup").permitAll()
            .anyRequest().authenticated()
        );
        
        setLoginView(http, LoginView.class);
    }
    
	
    @Override
    protected void configure(WebSecurity web) throws Exception {
        // Para Spring Security 6.x com Vaadin, use:
        web.ignoring().requestMatchers(
            "/VAADIN/**",
            "/favicon.ico",
            "/robots.txt",
            "/manifest.webmanifest",
            "/sw.js",
            "/offline.html",
            "/icons/**",
            "/images/**",
            "/frontend/**",
            "/webjars/**",
            "/h2-console/**",
            "/frontend-es5/**", 
            "/frontend-es6/**",
            "/login",
            "/signup"
        );
        
        super.configure(web);
    }
    
    */

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		var provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(encoder);
		return provider;
	}
	

}
