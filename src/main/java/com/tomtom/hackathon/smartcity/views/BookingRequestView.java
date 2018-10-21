package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.BookingRequest;
import com.tomtom.hackathon.smartcity.models.TimeWindowRequest;

public class BookingRequestView {

    private BookingRequest bookingRequest;
    private int bookingId;
    private String userName;


    public BookingRequestView(BookingRequest bookingRequest){ this.bookingRequest = bookingRequest;}

    public String getUserName(){
        return bookingRequest.getUserName();
    }

    public int getBookingId(){ return bookingRequest.getId(); }
}
