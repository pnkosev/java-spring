package app.web.controllers;

import app.web.models.binding.ItemCreateBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

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



        return super.redirect("/home");
    }

    @GetMapping("/merchant")
    public String merchant() {
        return "item/merchant";
    }
}
