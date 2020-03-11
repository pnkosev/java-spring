package pn.app.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pn.app.service.services.OfferService;
import pn.app.web.models.view.OfferViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final OfferService offerService;

    private final ModelMapper modelMapper;

    public HomeController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView model) {
        List<OfferViewModel> offers = this.offerService.getAll()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferViewModel.class))
                .collect(Collectors.toList());

        model.addObject("offers", offers);
        model.setViewName("index");

        return model;
    }
}
