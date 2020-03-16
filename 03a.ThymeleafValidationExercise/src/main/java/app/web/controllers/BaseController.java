package app.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {

    protected BaseController() { }

    protected ModelAndView view(String url, ModelAndView modelAndView) {
        modelAndView.setViewName(url);
        return modelAndView;
    }

    protected ModelAndView view(String url) {
        return this.view(url, new ModelAndView());
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }
}
