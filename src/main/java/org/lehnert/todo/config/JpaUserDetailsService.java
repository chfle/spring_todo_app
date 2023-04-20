package org.lehnert.todo.config;

import lombok.AllArgsConstructor;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * UserDetailsService using jpa users table
 */
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
   @Autowired
   private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Optional<Users> optionalUsers = userRepository.findUserByUsername(username);

        // check if user exists
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();

            // convert Users -> UserDetails
            return User.withUsername(users.getUsername())
                    .password(users.getPassword()).authorities("User").build();
        }

        throw new UsernameNotFoundException("Username was not found");
    }
}
