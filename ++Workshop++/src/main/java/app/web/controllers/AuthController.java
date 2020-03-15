package app.web.controllers;

import app.service.models.UserAuthenticatedServiceModel;
import app.service.models.UserLoginServiceModel;
import app.service.models.UserRegisterServiceModel;
import app.service.services.api.AuthService;
import app.web.models.binding.UserLoginBindingModel;
import app.web.models.binding.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final AuthService authService;

    private final ModelMapper modelMapper;

    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerPost(
            @ModelAttribute UserRegisterBindingModel bindingModel) {

        try {
            this.authService.register(this.modelMapper.map(bindingModel, UserRegisterServiceModel.class));
            return "redirect:/login";
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginPost(
            HttpSession session,
            @ModelAttribute UserLoginBindingModel bindingModel) {

        UserAuthenticatedServiceModel loggedInUser = this.authService.login(this.modelMapper.map(bindingModel, UserLoginServiceModel.class));

        if (loggedInUser != null) {
            session.setAttribute("user", loggedInUser);
            return "redirect:/home";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
