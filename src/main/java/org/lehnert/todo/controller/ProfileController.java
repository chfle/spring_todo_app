package org.lehnert.todo.controller;

import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    @Autowired
    private final UserRepository userRepository;
    @GetMapping
    String getProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        Optional<Users> optionalUsers = userRepository.findUserByUsername(username);

        if (optionalUsers.isPresent()) {
            Users user = optionalUsers.get();

            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
        }

        return "profile";
    }
}
