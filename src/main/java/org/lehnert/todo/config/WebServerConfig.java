package org.lehnert.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebServerConfig  {
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       // TODO: login without disable
       http.csrf().disable();
       http.cors();

       http.authorizeHttpRequests()
               .requestMatchers("/js/***", "/css/**").permitAll()
               .requestMatchers("/login").permitAll()
               .requestMatchers("/register").permitAll()
               .anyRequest().authenticated();

      http.formLogin().loginPage("/login").defaultSuccessUrl("/");

       return http.build();
   }
}
