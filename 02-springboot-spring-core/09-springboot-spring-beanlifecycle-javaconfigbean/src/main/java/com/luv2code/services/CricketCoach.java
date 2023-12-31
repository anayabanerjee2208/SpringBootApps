package com.luv2code.services;

import com.luv2code.interfaces.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach {
    public CricketCoach(){
        System.out.println("In Constructor " + getClass().getSimpleName());
    }

    //Define our init method
    /*@PostConstruct
    public void doStrutupStuff(){
        System.out.println(("In doMyStartupStuff " + getClass().getSimpleName()));
    }*/

    //Define our destroy method
    /*@PreDestroy
    public void doCleanupStuff(){
        System.out.println(("In doCleanupStuff " + getClass().getSimpleName()));
    }*/
    @Override
    public String getDailyWorkout() {
        return "Practice bowling for 30 mins please";
    }
}
