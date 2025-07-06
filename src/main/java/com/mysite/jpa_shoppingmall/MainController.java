package com.mysite.jpa_shoppingmall;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    /**
     * Handles HTTP GET requests to the root URL ("/") and returns the name of the main view to render.
     *
     * @return the logical view name "main"
     */
    @GetMapping("/")
    public String home(@RequestParam(value = "searchQuery", required = false) String searchQuery, Model model) {
        // todo: imjplement search logic using searchQuery(e.g. Querydsl, QuerydslPredicateExecutor
        return "main";
    }
}
