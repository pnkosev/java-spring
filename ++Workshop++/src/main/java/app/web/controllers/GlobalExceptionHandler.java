package app.web.controllers;

import app.error.HeroNotFoundException;
import app.error.UserNotFoundException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error/error";

    @ExceptionHandler(Throwable.class)
    public ModelAndView defaultErrorHandler(Throwable e) throws Throwable {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        mav.addObject("message", e.getMessage());
        return mav;
    }

    @ExceptionHandler(HeroNotFoundException.class)
    public ModelAndView heroNotFoundErrorHandler(RuntimeException e) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        mav.addObject("message", e.getMessage());
        return mav;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFoundErrorHandler(RuntimeException e) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        mav.addObject("message", e.getMessage());
        return mav;
    }
}
