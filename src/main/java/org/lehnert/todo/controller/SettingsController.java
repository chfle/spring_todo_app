package org.lehnert.todo.controller;

import lombok.AllArgsConstructor;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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

    @PutMapping("/password")
    @ResponseBody
    Boolean changePassword(@RequestBody Map<String, Object> payload) {
        System.out.println(payload);
        try {
            Optional<Users> optionalUsers = userRepository.findUserByUsername((String) payload.get("username"));

            if (optionalUsers.isPresent()) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                Users user = optionalUsers.get();

                // check if old pass is correct
                if (passwordEncoder.matches((String)payload.get("passwordOld"), user.getPassword())) {
                    // change password and save
                    user.setPassword(passwordEncoder.encode((String)payload.get("passwordNew")));
                    userRepository.save(user);
                    return true;
                }
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
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
