package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    public CricketCoach(){
        System.out.println("In Constructor " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice bowling for 30 mins please";
    }
}
