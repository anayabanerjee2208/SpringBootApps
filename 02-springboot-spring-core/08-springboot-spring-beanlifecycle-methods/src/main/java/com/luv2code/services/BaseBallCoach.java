package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class BaseBallCoach implements Coach {

    public BaseBallCoach(){
        System.out.println("In Constructor " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Spend 30mins on batting practice";
    }
}
