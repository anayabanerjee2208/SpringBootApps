package com.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;
    @BeforeEach
    void setup(){
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }
    @AfterEach
    void afterEach(){
        System.out.println("A unit test was finished.");
    }

    @Test
    void should_ReturnCorrectDietPlan_When_CorrectCoder(){
        //Given
        //Arrange
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        //When
        //Act
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //Then
        //Assert
        //assertEquals(expected, actual); This won't work because can't compare the objects directly
        assertAll(() -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()));
    }

}