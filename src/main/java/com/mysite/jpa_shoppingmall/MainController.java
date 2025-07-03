package com.mysite.jpa_shoppingmall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * Handles HTTP GET requests to the root URL ("/") and returns the name of the main view to render.
     *
     * @return the logical view name "main"
     */
    @GetMapping("/")
    public String main() {
        return "main";
    }
}
