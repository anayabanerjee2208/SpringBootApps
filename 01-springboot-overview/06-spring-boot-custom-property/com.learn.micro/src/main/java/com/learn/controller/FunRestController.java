package com.learn.controller;

import com.learn.model.FunModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
@Autowired
FunModel funModel;
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World" + funModel.getTeamName();
    }

    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Hello World Again";
    }
}
