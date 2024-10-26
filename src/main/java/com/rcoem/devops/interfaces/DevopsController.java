package com.rcoem.devops.interfaces;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hello")
public class DevopsController{

    @GetMapping
    String helloWorld(){
        return "Hello World";
    }


    

}