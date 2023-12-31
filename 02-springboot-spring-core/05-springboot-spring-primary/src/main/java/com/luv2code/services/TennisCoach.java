package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice your backend";
    }
}
