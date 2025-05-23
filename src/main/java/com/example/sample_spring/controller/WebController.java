package com.example.sample_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    @GetMapping("/register")
    public String register() {
        return "redirect:/registration.html";
    }

    @GetMapping("/products/create")
    public String createProduct() {
        return "redirect:/product-form.html";
    }
}