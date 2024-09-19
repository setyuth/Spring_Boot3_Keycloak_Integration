package com.springboot.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public String hello(){
        return "Hello World from spring boot 3 Keycloak Integration";
    }

    @GetMapping("/hello-2")
    public String hello2(){
        return "Hello World from spring boot 3 Keycloak Integration - ADMIN";
    }
}
