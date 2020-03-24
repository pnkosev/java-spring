package app.web.controllers.rest;

import app.service.models.item.ItemServiceModel;
import app.service.services.api.HeroService;
import app.service.services.api.ItemService;
import app.web.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemRestController extends BaseController {

    private final ItemService itemService;

    private final HeroService heroService;

    public ItemRestController(ItemService itemService, HeroService heroService) {
        this.itemService = itemService;
        this.heroService = heroService;
    }

    @GetMapping("/item")
    public List<ItemServiceModel> merchant(ModelAndView mov, HttpSession session) {
        return this.itemService.getAllItems(super.getHeroNameFromSession(session));
    }

    @PostMapping("/item/{itemName}")
    public void merchantPost(@PathVariable String itemName, HttpSession session) {
        this.heroService.updateHeroItem(super.getHeroNameFromSession(session), itemName);
    }
}
