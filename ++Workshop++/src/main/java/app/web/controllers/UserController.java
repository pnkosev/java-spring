package app.web.controllers;

import app.service.models.user.UserDetailsServiceModel;
import app.service.services.api.UserService;
import app.web.models.view.UserDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/profile")
    public ModelAndView profile(ModelAndView mov, HttpSession session) {
        UserDetailsServiceModel userServiceModel = this.userService.getUserByName(super.getUsernameFromSession(session));

        UserDetailsViewModel userViewModel = this.modelMapper.map(userServiceModel, UserDetailsViewModel.class);

        mov.addObject("user", userViewModel);

        return super.view("user/profile", mov);
    }
}
