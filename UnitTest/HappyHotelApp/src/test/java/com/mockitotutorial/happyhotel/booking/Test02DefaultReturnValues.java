package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test02DefaultReturnValues {

    private BookingService bookingService;
    private PaymentService paymentService;
    private  RoomService roomService;
    private BookingDAO bookingDAO;
    private MailSender mailSender;
    @BeforeEach
    void setup(){
        this.paymentService = mock(PaymentService.class);
        this.roomService = mock(RoomService.class);
        this.bookingDAO = mock(BookingDAO.class);
        this.mailSender = mock(MailSender.class);
        this.bookingService = new BookingService(paymentService, roomService,
                bookingDAO, mailSender);
    }
    @Test
    void should_CountAvailablePlaces(){
        //Given
        int expectedCount = 0;

        //When
        int actualCount = bookingService.getAvailablePlaceCount();
        //Then
        assertEquals(expectedCount, actualCount);


    }
}
