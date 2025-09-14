package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test03ReturnCustomValues {

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
    void should_CountAvailablePlaces_When_OneRoomAvailable(){
        //Given
        when(this.roomService.getAvailableRooms()).thenReturn(
                List.of(new Room("Room 1", 2))
        );
        int expectedCount = 2;
        //When
        int actualCount = bookingService.getAvailablePlaceCount();

        //Then
        assertEquals(expectedCount, actualCount);

    }
    @Test
    void should_CountAvailablePlaces_When_MultipleRoomsAvailable(){
        //Given
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room 1", 2));
        rooms.add(new Room("Room 2", 3));
        when(this.roomService.getAvailableRooms()).thenReturn(
                rooms);
        int expectedCount = 5;
        //When
        int actualCount = bookingService.getAvailablePlaceCount();

        //Then
        assertEquals(expectedCount, actualCount);

    }
}
