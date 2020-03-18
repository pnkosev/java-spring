package app.web.controllers;

import app.service.services.CapitalService;
import app.web.models.CapitalListViewModel;
import app.web.models.VirusFormViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;

    private final ModelMapper modelMapper;

    public VirusController(CapitalService capitalService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(
            ModelAndView modelAndView,
            VirusFormViewModel virusForm) {
        
        modelAndView.addObject("capitals", getAllCapitals());
        return super.view("add", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addPost(
            ModelAndView modelAndView,
            @Valid VirusFormViewModel virusForm,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("capitals", getAllCapitals());
            return super.view("add", modelAndView);
        }

        return super.redirect("/viruses/show");
    }

    @GetMapping("/show")
    public ModelAndView show() {
        return super.view("show");
    }

    private List<CapitalListViewModel> getAllCapitals() {
        return this.capitalService
                .getAllCapitals()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList());
    }
}
