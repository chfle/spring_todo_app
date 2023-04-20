package org.lehnert.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Configuration for user login
 */
@Configuration
public class UserConfiguration {

    /**
     * Manage users
     */
    @Bean
    UserDetailsManager userDetailsManager() {
        var inMem = new  InMemoryUserDetailsManager();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        inMem.createUser(
                User.withUsername("chris")
                        .password(passwordEncoder.encode("chris"))
                        .authorities("Admin")
                        .build());

        inMem.createUser(
                User.withUsername("felix")
                        .password(passwordEncoder.encode("felix"))
                        .authorities("User")
                        .build()
        );

        return inMem;
    }

    /**
     * Password encoding
     */
    @Bean
    PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
    }
}
