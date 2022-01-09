package application.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller to handle exceptions.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public String exception(Exception exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "exception";
    }
}
