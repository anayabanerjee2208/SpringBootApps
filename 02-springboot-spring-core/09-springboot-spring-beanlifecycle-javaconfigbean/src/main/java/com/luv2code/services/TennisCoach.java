package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    public TennisCoach(){
        System.out.println("In Constructor " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice your backend";
    }
}
