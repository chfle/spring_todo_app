package org.lehnert.todo.controller;

import lombok.AllArgsConstructor;
import org.lehnert.todo.database.repository.ProfilePicRepository;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.ProfilePic;
import org.lehnert.todo.database.tables.Users;
import org.lehnert.todo.helper.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ProfilePicRepository profilePicRepository;

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

            // check if user has a profile
            Optional<ProfilePic> optionalProfilePic = profilePicRepository.findProfilePicByUserId(
                    optionalUsers.get().getId());

            model.addAttribute("hasProfilePic", optionalProfilePic.isPresent());
        }

        return "profile";
    }

    /**
     * This controller is responsible for retrieving the profile picture of a user by their username.
     *
     * @param username The username of the user whose profile picture needs to be retrieved.
     * @return ResponseEntity containing the profile picture image in byte array form and the appropriate HTTP status code.
     */
    @GetMapping("/pic/{username}")
    ResponseEntity<?> getProfileByUsername(@PathVariable(name = "username") String username) {
        Optional<Users> optionalUsers = userRepository.findUserByUsername(username);

        if (optionalUsers.isPresent()) {
            Optional<ProfilePic> optionalProfilePic = profilePicRepository.findProfilePicByUserId(optionalUsers.get().getId());

            if (optionalProfilePic.isPresent()) {
                byte[] image = ImageUtil.decompressImage(optionalProfilePic.get().getImageData());

                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.valueOf(optionalProfilePic.get().getType()))
                        .body(image);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Updates user profile information based on the authenticated user's username.
     *
     * @param payload a map containing key-value pairs of user information to update
     * @param authentication the authentication object containing the user's username
     *
     * @return a boolean indicating whether the user profile update was successful
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

    /**
     * Uploads the given photo as the user's profile picture, if the user exists.
     *
     * @param username the username of the user whose profile picture is being uploaded
     * @param photo the MultipartFile representing the photo to upload as the profile picture
     *
     * @return true if the photo was uploaded successfully, false otherwise
     */
    @ResponseBody
    @PostMapping("/picture")
    Boolean uploadPhoto(@RequestParam(name = "username") String username, @RequestPart MultipartFile photo)  {
        Optional<Users> optionalUsers = userRepository.findUserByUsername(username);

        if (optionalUsers.isPresent()) {
            ProfilePic profilePic = new ProfilePic();

            // check if user has a profile pic
            Optional<ProfilePic> currentPic = profilePicRepository.findProfilePicByUserId(optionalUsers.get().getId());

            currentPic.ifPresent((c) -> profilePicRepository.deleteByUserId(c.getUsers().getId()));

            try {
                profilePic.setType(photo.getContentType());
                profilePic.setImageData(ImageUtil.compressImage(photo.getBytes()));
                profilePic.setUsers(optionalUsers.get());
                // save
                profilePicRepository.save(profilePic);
                return true;
            }catch (Exception ignore) {}
        }

        return false;
    }

    /**
     * Deletes the profile picture of a user with the given username.
     *
     * @param username the username of the user whose profile picture is to be deleted
     * @return true if the profile picture was deleted successfully, false otherwise
     */
    @DeleteMapping("/deleteProfilePic")
    @ResponseBody
    Boolean deleteProfilePic(@RequestParam(name = "username") String username) {
        Optional<Users> optionalUsers = userRepository.findUserByUsername(username);

        if (optionalUsers.isPresent()) {
            profilePicRepository.deleteByUserId(optionalUsers.get().getId());
            return true;
        }

        return false;
    }
}