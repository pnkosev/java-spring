package app.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView index() {
        return this.view("index");
    }
}
