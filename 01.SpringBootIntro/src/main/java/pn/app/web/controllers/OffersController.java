package pn.app.web.controllers;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pn.app.service.models.OfferServiceModel;
import pn.app.service.services.OfferService;
import pn.app.web.models.OfferFindBindingModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OffersController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("register")
    public ModelAndView registerConfirm(@ModelAttribute OfferServiceModel offerServiceModel) {

        this.offerService.create(offerServiceModel);

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
