package org.lehnert.todo.controller;

import lombok.AllArgsConstructor;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    private final UserRepository userRepository;

    /**
     * Get settings page
     */
    @GetMapping
    String getSettings(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "settings/settings";
    }

    /**
     * Get delete account page
     */
    @GetMapping("/delete")
    String getDeleteSetting(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "/settings/delete";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    Boolean deleteAccountByUsername(@RequestParam(name = "username") String username) {
        Optional<Users> optionalUser = userRepository.findUserByUsername(username);

        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());

            // check if user was really deleted
            return userRepository.findUserByUsername(username).isEmpty();
        }

        return false;
    }
}
