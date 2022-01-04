package com.sp.example.MySpringCloud.controller;

import com.sp.example.MySpringCloud.configuration.ConfigProperty;
import com.sp.example.MySpringCloud.services.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private ConfigProperty configProperty;

    @GetMapping("/limits")
    public Limit getLimitDetails(){

        return new Limit(configProperty.getMinium(),configProperty.getMaximum());
        //return new Limit(1,1000);
    }
}
