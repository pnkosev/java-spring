package pn.app.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pn.app.service.models.CarServiceModel;
import pn.app.service.services.CarService;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("create");
    }

    @PostMapping("/create")
    public ModelAndView createConfirm(
            ModelAndView modelAndView,
            @ModelAttribute CarServiceModel car) {

        this.carService.save(car);

        modelAndView.setViewName("redirect:/cars/all");

        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {

        List<CarServiceModel> cars = this.carService.listAll();

        modelAndView.addObject("cars", cars);

        modelAndView.setViewName("all");

        return modelAndView;
    }
}
