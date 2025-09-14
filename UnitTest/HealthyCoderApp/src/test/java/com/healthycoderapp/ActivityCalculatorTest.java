package com.healthycoderapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityCalculatorTest {
    @Test
    void should_ReturnBad_When_AvgBelow20(){
        //Given

        int weeklyCardioMin = 40;
        int weeklyWorkoutSessions = 1;

        //When
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        //Then
        assertEquals("bad", actual);
    }
    @Test
    void should_ReturnAverage_When_AgeBetween20And40(){


    }
    @Test
    void should_ReturnGood_When_AvgBelow40(){


    }
}