package app.web.controllers;

import app.service.models.hero.HeroDetailsServiceModel;
import app.service.models.hero.HeroServiceModel;
import app.service.models.user.UserAuthenticatedServiceModel;
import app.service.services.api.HeroService;
import app.web.models.binding.HeroCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/hero")
public class HeroController extends BaseController {

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

    @GetMapping("/details/{heroName}")
    public ModelAndView details(@PathVariable String heroName, ModelAndView mov) {
        HeroDetailsServiceModel hero = this.heroService.getByHeroName(heroName);
        mov.addObject("hero", hero);
        return super.view("hero/hero-details", mov);
    }
}
