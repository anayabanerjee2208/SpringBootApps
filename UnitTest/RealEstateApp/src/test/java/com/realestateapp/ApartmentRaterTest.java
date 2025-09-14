package com.realestateapp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApartmentRaterTest {

    @ParameterizedTest
    @CsvSource(value = {"72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2"})
    void should_ReturnCorrectRating_When_CorrectApartment(Double apartmentArea,
                                                          Double apartmentPrice,
                                                          int apartmentRating){
        //Given
        Apartment apartment = new Apartment(apartmentArea, new BigDecimal(apartmentPrice));
        int rating = apartmentRating;

        //When
        int actual = ApartmentRater.rateApartment(apartment);

        //Then
        assertEquals(rating, actual);
    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment(){
        //Given
        Apartment apartment = new Apartment(0.0, new BigDecimal(200000.0));
        int expected = -1;

        //When
        int actual = ApartmentRater.rateApartment(apartment);

        //Then
        assertEquals(expected, actual);

    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList(){
        //Given
        List<Apartment> apartments = List.of(
                new Apartment(72.0, new BigDecimal(250000.0)),
                new Apartment(48.0, new BigDecimal(350000.0)),
                new Apartment(30.0, new BigDecimal(600000.0))
        );
        double expected = 1.0;

        //When
        double actual = ApartmentRater.calculateAverageRating(apartments);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList(){
        //Given
        List<Apartment> apartments = List.of();
        //When
        Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
        //Then
        assertThrows(RuntimeException.class, executable);
    }
}
