package com.mysite.jpa_shoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class JpaShoppingmallApplication {

	/**
	 * Launches the Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(JpaShoppingmallApplication.class, args);
	}

	/*@GetMapping("/")
	public String hello() {
		return "Hello World";
	}*/

}
