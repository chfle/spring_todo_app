package org.lehnert.todo.config;

import lombok.AllArgsConstructor;
import org.lehnert.todo.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity()
@AllArgsConstructor
public class WebServerConfig {
    @Autowired
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO: login without disable
        http.csrf().disable();
        http.cors();

        http.authorizeHttpRequests()
                .requestMatchers("/js/***", "/css/**", "/image/**").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated();

        http.formLogin().loginPage("/login").defaultSuccessUrl("/");

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Custom UserDetailsService
     *
     * @return
     */
    @Bean
    JpaUserDetailsService jpaUserDetailsService() {
        return new JpaUserDetailsService(userRepository);
    }
}
