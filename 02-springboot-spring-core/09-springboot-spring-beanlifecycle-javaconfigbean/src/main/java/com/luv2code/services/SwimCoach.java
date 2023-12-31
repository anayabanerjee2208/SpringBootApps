package com.luv2code.services;

import com.luv2code.interfaces.Coach;

public class SwimCoach implements Coach {

    public SwimCoach(){
        System.out.println("In Constructor " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Swim 100mts as warmup";
    }
}
