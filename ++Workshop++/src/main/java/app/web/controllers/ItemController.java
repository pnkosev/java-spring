package app.web.controllers;

import app.service.models.item.ItemCreateServiceModel;
import app.service.services.api.ItemService;
import app.web.models.binding.ItemCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

    private final ItemService itemService;

    private final ModelMapper modelMapper;

    public ItemController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView create(ItemCreateBindingModel itemCreateBindingModel) {
        return super.view("item/item-create");
    }

    @PostMapping("/create")
    public ModelAndView createPost(
            @Valid ItemCreateBindingModel itemCreateBindingModel,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("/item/item-create");
        }

        this.itemService
                .create(this.modelMapper.map(itemCreateBindingModel, ItemCreateServiceModel.class));

        return super.redirect("/home");
    }

    @GetMapping("/merchant")
    public String merchant() {
        return "item/merchant";
    }
}
