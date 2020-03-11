package pn.app.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pn.app.service.models.OfferServiceModel;
import pn.app.service.services.OfferService;
import pn.app.web.models.binding.OfferFindBindingModel;
import pn.app.web.models.binding.OfferRegisterBindingModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferService offerService;

    private final ModelMapper modelMapper;

    public OffersController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("register")
    public ModelAndView registerConfirm(@ModelAttribute OfferRegisterBindingModel offer) {

        try {
            this.offerService.create(this.modelMapper.map(offer, OfferServiceModel.class));
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return new ModelAndView("redirect:/offers/register");
        }

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/find")
    public ModelAndView find() {
        return new ModelAndView("find");
    }

    @PostMapping("/find")
    public ModelAndView findConfirm(
            @Valid OfferFindBindingModel offerFindBindingModel,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            OfferServiceModel offer = this.offerService.findOneByApartmentTypeAndApartmentRent(offerFindBindingModel.getFamilyApartmentType(), offerFindBindingModel.getFamilyBudget());

            if (offer != null) {
                this.offerService.delete(offer);
                return new ModelAndView("redirect:/");
            }
        }

        return new ModelAndView("redirect:/offers/find");
    }
}
