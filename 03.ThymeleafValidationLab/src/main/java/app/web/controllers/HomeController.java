package app.web.controllers;

import app.web.views.PersonFormViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeGet(PersonFormViewModel personForm) {
        return "form";
    }

    @PostMapping("/")
    public String homePost(@Valid PersonFormViewModel personForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }

    @GetMapping("/results")
    public String results() {
        return "results";
    }
}
