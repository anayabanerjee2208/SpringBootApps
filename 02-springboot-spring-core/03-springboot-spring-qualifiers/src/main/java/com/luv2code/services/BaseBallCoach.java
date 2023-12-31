package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class BaseBallCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Spend 30mins on batting practice";
    }
}
