package org.lehnert.todo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainPageController {
    @GetMapping
    String getMainPage(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());

        return "mainPage";
    }
}
