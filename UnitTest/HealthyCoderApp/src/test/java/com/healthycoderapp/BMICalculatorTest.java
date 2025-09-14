package com.healthycoderapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class BMICalculatorTest {
    private String environment = "prod";
    @BeforeAll //must be static, generally for DB connection, file connection, etc.
    static void beforeAll(){
        System.out.println("Before all unit tests");
    }

    @AfterAll //must be static, generally for DB connection, file connection, etc.
    static void afterAll(){
        System.out.println("After all unit tests");
    }

    @Nested
    @DisplayName(">>>>>IsDietRecommendedTests<<<<<<")
    class IsDietRecommendedTests{
        @Test
        @DisplayName(">>>>>Sample Test Case<<<<<<")
       // @Disabled //Disabled the test case
        @DisabledOnOs(OS.WINDOWS)
        void should_ReturnTrue_When_DietRecommended(){
            //Given
            //Arrange
            double weight = 89.0;
            double height = 1.72;

            //When
            //Act
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            //Assert
            assertTrue(recommended);
            assertTrue(BMICalculator.isDietRecommended(81.2, 1.65));
        }
        @Test
        void should_ReturnFalse_When_DietNotRecommended(){
            //Given
            //Arrange
            double weight = 50.0;
            double height = 1.92;

            //When
            //Act
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            //Assert
            assertFalse(recommended);
        }
        @Test
        void should_ThrowArithmeticException_When_HeightZero(){
            //Given
            //Arrange
            double weight = 89.0;
            double height = 0.0;

            //When
            //Act

            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

            //Then
            //Assert
            assertThrows(ArithmeticException.class, executable);
        }
        @ParameterizedTest
        @ValueSource(doubles = {89.0, 95.0, 110.0})
        void should_ReturnTrue_When_DietRecommended_Parameterized(Double coderWeight){
            //Given
            //Arrange
            double height = 1.72;
            double weight = coderWeight;

            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            //Assert
            assertTrue(recommended);
            assertTrue(BMICalculator.isDietRecommended(81.2, 1.65));
        }
        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
        void should_ReturnTrue_When_DietRecommended_BothValueParameterized(Double coderWeight, Double coderHeight){
            //Given
            //Arrange
            double height = coderHeight;
            double weight = coderWeight;

            //When
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            //Assert
            assertTrue(recommended);
            assertTrue(BMICalculator.isDietRecommended(81.2, 1.65));
        }

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        void should_ReturnTrue_When_DietRecommended_BothValueParameterizedCSVFile(Double coderWeight, Double coderHeight){
            //Given
            //Arrange
            double height = coderHeight;
            double weight = coderWeight;

            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            //Assert
            assertTrue(recommended);
            assertTrue(BMICalculator.isDietRecommended(81.2, 1.65));
        }


        @RepeatedTest(value = 5, name =RepeatedTest.LONG_DISPLAY_NAME)
        void should_ReturnTrue_When_DietRecommended_RepeatedTest(){
            //Given
            //Arrange
            double weight = 89.0;
            double height = 1.72;

            //When
            //Act
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            //Then
            //Assert
            assertTrue(recommended);
            assertTrue(BMICalculator.isDietRecommended(81.2, 1.65));
        }
    }

    @Nested
    class FindCoderWithWorstBMITests{
        @Test
        void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty(){
            //Given
            //Arrange
            List<Coder> coder = List.of(
                    new Coder(1.80, 60.0),
                    new Coder(1.82, 98.0),
                    new Coder(1.82, 64.7));
            //When
            //Act
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coder);

            //Then
            //Assert
            assertAll(
                    () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                    () -> assertEquals(98.0, coderWorstBMI.getWeight()));

        }
        @Test
        void should_ReturnCoderWithWorstBMIInOneMilSecond_When_CoderListHasTenThousandRecords(){
            //Given
            //Arrange
            assumeTrue(BMICalculatorTest.this.environment.equals("prod")); // this will run the test only in prod environment
            List<Coder> coder = new ArrayList<>();
            for(int i=0; i<10000; i++){
                coder.add(new Coder(1.0+i, 10.0+i));
            }
            //When
            //Act
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coder);

            //Then
            //Assert
            assertTimeout(Duration.ofMillis(500), executable);

        }
        @Test
        void should_ReturnNullWorstBMICoder_When_CoderListEmpty(){
            //Given
            List<Coder> coder = List.of();
            //When
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coder);
            //Then
            assertNull(coderWorstBMI);

        }
    }
@Nested
class GetBMIScoresTests{
    @Test
    void should_ReturnBMIScores_When_CoderListNotEmpty(){
        //Given
        List<Coder> coder = List.of(
                new Coder(1.80, 60.0),
                new Coder(1.82, 98.0),
                new Coder(1.82, 64.7)
        );
        double[] expected = {18.52, 29.59, 19.53};
        //When
        double[] bmiScores = BMICalculator.getBMIScores(coder);

        //Then
        assertArrayEquals(expected, bmiScores);

    }

    }


}