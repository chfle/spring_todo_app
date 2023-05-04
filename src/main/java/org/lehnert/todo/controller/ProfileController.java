package org.lehnert.todo.controller;

import lombok.AllArgsConstructor;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    @Autowired
    private final UserRepository userRepository;

    /**
     * Get user profile page
     */
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

    /**
     * Update user profile
     *
     * @param payload user profile information
     */
    @PutMapping
    @ResponseBody
    Boolean updateProfile(@RequestBody Map<String, Object> payload, Authentication authentication) {
        String username = authentication.getName();
        Optional<Users> optionalUsers = userRepository.findUserByUsername(username);

        if (optionalUsers.isPresent()) {
           Users user = optionalUsers.get();

           try {
               // check if username or email exists
               String newEmail = (String)payload.get("email");
               String newUserName = (String)payload.get("username");

               if (!newEmail.equals(user.getEmail()) && userRepository.findUserByEmail(newEmail).isEmpty()) {
                   user.setEmail(newEmail);
               }

               if (!newUserName.equals(user.getUsername()) && userRepository.findUserByUsername(newUserName).isEmpty()) {
                   user.setUsername(newUserName);
               }

               userRepository.save(user);
               return true;
           }catch (Exception exception) {
               exception.printStackTrace();
               return false;
           }
        }

        return false;
    }

    @ResponseBody
    @PostMapping("/picture")
    Boolean uploadPhoto(@RequestParam(name = "username") String username, @RequestPart MultipartFile photo) {
        System.out.println(photo);
        return false;
    }
}
