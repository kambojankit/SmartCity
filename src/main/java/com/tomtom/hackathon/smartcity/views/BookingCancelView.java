package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.BookingRequest;

public class BookingCancelView {

    private BookingRequest bookingRequest;
    private int bookingId;
    private String userName;
    private String requestStatus;


    public BookingCancelView(BookingRequest bookingRequest){ this.bookingRequest = bookingRequest;}

    public String getUserName(){ return bookingRequest.getUserName(); }

    public int getBookingId(){ return bookingRequest.getId(); }

    public String getRequestStatus() { return bookingRequest.getRequestStatus(); }
}
