package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}