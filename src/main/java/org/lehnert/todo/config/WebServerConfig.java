package org.lehnert.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@EnableWebSecurity
@Configuration
public class WebServerConfig  {
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.httpBasic()
               .and().formLogin()
               .and().rememberMe();

       return http.build();
   }
}
