package app.web.controllers;

import app.service.models.item.ItemCreateServiceModel;
import app.service.models.item.ItemServiceModel;
import app.service.services.api.HeroService;
import app.service.services.api.ItemService;
import app.web.models.binding.ItemCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

    private final ItemService itemService;

    private final HeroService heroService;

    private final ModelMapper modelMapper;

    public ItemController(ItemService itemService, HeroService heroService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.heroService = heroService;
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

        this.itemService.create(this.modelMapper.map(itemCreateBindingModel, ItemCreateServiceModel.class));

        return super.redirect("/home");
    }

    @GetMapping("/merchant")
    public ModelAndView merchant(ModelAndView mov, HttpSession session) {
        List<ItemServiceModel> allItems = this.itemService.getAllItems(super.getHeroNameFromSession(session));
        mov.addObject("items", allItems);
        return super.view("item/merchant", mov);
    }

    @PostMapping("/merchant/{itemName}")
    public ModelAndView merchantPost(@PathVariable String itemName, HttpSession session) {
        this.heroService.updateHeroItem(super.getHeroNameFromSession(session), itemName);

        return super.redirect("/item/merchant");
    }
}
