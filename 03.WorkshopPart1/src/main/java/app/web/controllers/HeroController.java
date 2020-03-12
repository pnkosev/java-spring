package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hero")
public class HeroController {

    @GetMapping("/create")
    public String create() {
        return "hero/hero-create";
    }

    @GetMapping("/details/{name}")
    public String details(@PathVariable String name) {
        return "hero/hero-details";
    }
}
