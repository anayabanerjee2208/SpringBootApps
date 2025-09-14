package com.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class RandomApartmentGeneratorTest {

    private RandomApartmentGenerator generator;
    private static final double MAX_MULTIPLIER = 4.0;
    @BeforeEach
    void setup(){
        this.generator =new RandomApartmentGenerator();
    }

    @Test
    void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice(){
        //given
        double minArea = 30.0;
        BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
        double maxArea = minArea * MAX_MULTIPLIER;
        BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

        //When
        Apartment apartment = generator.generate();
        BigDecimal maxPrice  = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
        BigDecimal minPrice  = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);

        //Then
         assertAll(
                 () -> assertTrue(apartment.getArea() >=  minArea),
                 () -> assertTrue(apartment.getArea() <= maxArea)
                 );

    }
}
