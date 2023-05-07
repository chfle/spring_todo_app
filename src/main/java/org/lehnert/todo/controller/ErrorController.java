package org.lehnert.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The ErrorController class is a Spring MVC controller that handles requests for the '/error'
 * endpoint, and returns the 'error' view.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    @GetMapping
    String getErrorPage() {
        return "error";
    }
}
