package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Run 5k";
    }
}
