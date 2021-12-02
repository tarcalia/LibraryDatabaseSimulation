package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main controller for Spring Boot application
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String mainPage() {
        return "index";
    }

}
