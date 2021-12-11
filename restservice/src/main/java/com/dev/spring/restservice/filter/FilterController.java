package com.dev.spring.restservice.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @GetMapping(path = "/filter")
    public void staticFilter(){
    }
}
