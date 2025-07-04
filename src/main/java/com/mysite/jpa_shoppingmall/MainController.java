package com.mysite.jpa_shoppingmall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * Handles HTTP GET requests to the root URL and returns the "main" view.
     *
     * @return the logical name of the main view to render
     */
    @GetMapping("/")
    public String main() {
        return "main";
    }
}
