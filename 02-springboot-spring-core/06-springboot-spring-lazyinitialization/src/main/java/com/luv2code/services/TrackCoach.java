package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrackCoach implements Coach {
    public TrackCoach(){
        System.out.println("In Constructor " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Run 5k";
    }
}
