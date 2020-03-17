package app.web.controllers;

import app.web.models.VirusFormViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    @GetMapping("/add")
    public ModelAndView add(VirusFormViewModel virusForm) {
        return super.view("add");
    }

    @PostMapping("/add")
    public ModelAndView addPost(
            @Valid VirusFormViewModel virusForm,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return super.view("add");
        }

        return super.redirect("/viruses/show");
    }
}
