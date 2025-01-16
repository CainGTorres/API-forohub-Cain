package com.caingt.Forohub.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class PrincipalController {

    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }

}
