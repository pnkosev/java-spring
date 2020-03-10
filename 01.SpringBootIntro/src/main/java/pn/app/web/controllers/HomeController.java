package pn.app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pn.app.service.services.OfferService;

@Controller
public class HomeController {

    private final OfferService offerService;

    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView model) {
        model.addObject("offers", this.offerService.getAll());
        model.setViewName("index");

        return model;
    }
}
