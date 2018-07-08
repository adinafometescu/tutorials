package com.tutorials.spring.eureka.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private FeignCoffee coffeeClient;

    @RequestMapping("/test")
    public String getCoffee() {
        return coffeeClient.getCoffee();
    }
}
