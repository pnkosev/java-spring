package app.web.controllers;

import app.service.models.HeroServiceModel;
import app.service.models.UserAuthenticatedServiceModel;
import app.service.services.api.HeroService;
import app.web.models.binding.HeroCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/hero")
public class HeroController {

    private final HeroService heroService;

    private final ModelMapper modelMapper;

    public HeroController(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String create() {
        return "hero/hero-create";
    }

    @PostMapping("/create")
    public String createPost(
            HttpSession session,
            @ModelAttribute HeroCreateBindingModel hero) {

        UserAuthenticatedServiceModel user = (UserAuthenticatedServiceModel) session.getAttribute("user");

        hero.setUserUsername(user.getUsername());

        try {
            this.heroService.create(this.modelMapper.map(hero, HeroServiceModel.class));
            user.setHeroName(hero.getName());
            session.setAttribute("user", user);
            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/create";
        }

    }

    @GetMapping("/details/{name}")
    public String details(@PathVariable String name) {
        return "hero/hero-details";
    }
}
