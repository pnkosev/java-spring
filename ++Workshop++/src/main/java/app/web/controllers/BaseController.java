package app.web.controllers;

import app.error.UserNotFoundException;
import app.service.models.user.UserAuthenticatedServiceModel;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected BaseController() {}

    protected ModelAndView view(String viewName, ModelAndView mov) {
        mov.setViewName(viewName);
        return mov;
    }

    protected ModelAndView view(String viewName) {
        return this.view(viewName, new ModelAndView());
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }

    protected String getUsernameFromSession(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new UserNotFoundException("No user currently connected!");
        }

        return ((UserAuthenticatedServiceModel)session.getAttribute("user")).getUsername();
    }

    protected String getHeroNameFromSession(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new UserNotFoundException("No user currently connected!");
        }

        return ((UserAuthenticatedServiceModel)session.getAttribute("user")).getHeroName();
    }
}
