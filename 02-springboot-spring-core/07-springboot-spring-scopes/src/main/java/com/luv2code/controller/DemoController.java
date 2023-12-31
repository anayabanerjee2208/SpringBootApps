package com.luv2code.controller;

import com.luv2code.interfaces.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //Define private field for dependency
    private Coach _myCoach;
    private Coach _anotherCoach;

    //Define constructor for dependency injection
    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach myCoach, @Qualifier("cricketCoach") Coach anotherCoach){
        System.out.println("In Constructor " + getClass().getSimpleName());
        _myCoach = myCoach;
        _anotherCoach = anotherCoach;
    }
    /*@Autowired
    public void set_myCoach(Coach _myCoach){
        this._myCoach=_myCoach;
    }*/

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return _myCoach.getDailyWorkout();
    }

    @GetMapping("/beanCheck")
    public String check(){
        return "Comparing Beans: myCoach == anotherCoach, " + (_myCoach == _anotherCoach);
    }

}
