package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {
    /***
     * If Number is divisible by 3, print Fizz
     * If Number is divisible by 5, print Buzz
     * If Number is divisible by 3 and 5, print FizzBuzz
     * If Number is not divisible by 3 or 5, print the number
     */

    //If number is divisible by 3
    @Test
    @DisplayName("Divisible by 3")
    @Order(1)
    void testDivisibleByThree(){
        String expectedValue = "Fizz";
        assertEquals(expectedValue, FizzBuzz.compute(3), "Should Return Fizz");
       // fail("fail"); //Fail is an assertion to make the initial test fail.First test to fail the method
    }

    //If number is divisible by 5
    @Test
    @DisplayName("Divisible by 5")
    @Order(2)
    void testDivisibleByFive(){
        String expectedValue = "Buzz";
        assertEquals(expectedValue, FizzBuzz.compute(5), "Should Return Buzz");
    }

    //If number is divisible by 3 and 5
    @Test
    @DisplayName("Divisible by both 3 and 5")
    @Order(3)
    void testDivisibleByThreeAndFive(){
        String expectedValue = "FizzBuzz";
        assertEquals(expectedValue, FizzBuzz.compute(15), "Should Return FizzBuzz");
    }

    //If number is not divisible by 3 and 5
    @Test
    @DisplayName("Not Divisible by 3 and 5")
    @Order(4)
    void testNotDivisibleByThreeAndFive(){
        String expectedValue = "1";
        assertEquals(expectedValue, FizzBuzz.compute(1), "Should Return 1");
    }


    @DisplayName("Testing with small data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @Order(5)
    @CsvFileSource(resources = "/small-test-data.csv")
    void testSmallDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with medium data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @Order(6)
    @CsvFileSource(resources = "/medium-test-data.csv")
    void testMediumDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with large data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @Order(7)
    @CsvFileSource(resources = "/large-test-data.csv")
    void testLargeDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with array of data")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvSource({"1,1",
                "2,2",
                "3,Fizz"
    })
    @Order(8)
    void testArrayOfDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }
}
