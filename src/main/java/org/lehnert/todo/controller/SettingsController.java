package org.lehnert.todo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    @GetMapping
    String getSettings(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "settings/settings";
    }

    @GetMapping("/delete")
    String getDeleteSetting(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "/settings/delete";
    }
}
