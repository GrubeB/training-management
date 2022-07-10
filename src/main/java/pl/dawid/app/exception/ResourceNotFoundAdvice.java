package pl.dawid.app.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ResourceNotFoundAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView ResourceNotFoundHandler(ResourceNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("exception/show-resource-not-found");
        modelAndView.addObject("exception", exception.getMessage());
        return modelAndView;
    }
}
