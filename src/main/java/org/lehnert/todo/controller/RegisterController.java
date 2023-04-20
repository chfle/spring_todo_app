package org.lehnert.todo.controller;

import lombok.AllArgsConstructor;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {
    @Autowired
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
    /**
     * Get register page
     * @return
     */
    @GetMapping
    String getRegister() {
        return "register";
    }

    /**
     * Post register data
     * @param payload user data
     * @return true or false
     */
    @PostMapping
    @ResponseBody
    Boolean registerNewUser(@RequestBody Map<String, Object> payload) {
        // get data from payload
        try {
            String username = (String)payload.get("name");
            String email = (String)payload.get("email");
            String plainPassword = (String)payload.get("password");

            // check if username or email exists
            if (userRepository.findUserByUsernameOrEmail(username, email).isEmpty()) {
                // Todo: save password with salt
                Users user = new Users();
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(plainPassword));
                user.setFirstLogin(new Date(new java.util.Date().getTime()));

                userRepository.save(user);
                return true;
            }
        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return false;
    }
}
