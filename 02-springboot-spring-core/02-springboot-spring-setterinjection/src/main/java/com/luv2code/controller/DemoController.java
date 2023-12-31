package com.luv2code.controller;

import com.luv2code.interfaces.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //Define private field for dependency
    private Coach _myCoach;

    //Define constructor for dependency injection
    /*@Autowired
    public DemoController(Coach myCoach){
        _myCoach = myCoach;
    }*/
    @Autowired
    public void set_myCoach(Coach _myCoach){
        this._myCoach=_myCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return _myCoach.getDailyWorkout();
    }

}
