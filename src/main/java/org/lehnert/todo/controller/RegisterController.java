package org.lehnert.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {
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
            String name = (String)payload.get("name");
            String email = (String)payload.get("email");
            String plainPassword = (String)payload.get("password");



            return true;
        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
