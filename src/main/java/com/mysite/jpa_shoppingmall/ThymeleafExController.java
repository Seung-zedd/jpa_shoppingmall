package com.mysite.jpa_shoppingmall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafExController {

    /**
     * Handles HTTP GET requests to "/ex07" and returns the view name for the Thymeleaf example page.
     *
     * @return the logical view name "thymeleafEx/thymeleafEx07" for rendering the corresponding Thymeleaf template
     */
    @GetMapping("/ex07")
    public String thymeleafExample07() {
        return "thymeleafEx/thymeleafEx07";
    }
}
