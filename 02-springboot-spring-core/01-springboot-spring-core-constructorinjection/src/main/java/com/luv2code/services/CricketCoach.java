package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice bowling";
    }
}
