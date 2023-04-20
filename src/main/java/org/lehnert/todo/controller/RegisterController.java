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
        System.out.println(payload);
        // TODO: logic...
        return true;
    }
}
