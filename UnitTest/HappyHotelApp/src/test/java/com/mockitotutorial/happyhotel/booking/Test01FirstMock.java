package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test01FirstMock {

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
    void should_CalculateCorrectPrice_When_Correct_Input() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024,01,01),
                LocalDate.of(2024,01,5), 2, false);
        double expectedPrice = 4*50.0*2;
        // when
        double actualPrice  = bookingService.calculatePrice(bookingRequest);
        // then
        assertEquals(expectedPrice, actualPrice);
    }
}
