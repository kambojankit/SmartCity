package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.BookingRequest;
import com.tomtom.hackathon.smartcity.models.TimeWindowRequest;

public class BookingHistoryView {

    private BookingRequest bookingRequest;
    private TimeWindowRequest timeWindowRequest;
    private int bookingId;
    private String userName;
    private String address;
    private String tokenNumber;
    private String timeWindow;
    private String requestStatus;


    public BookingHistoryView(BookingRequest bookingRequest){ this.bookingRequest = bookingRequest;}

    public String getUserName(){
        return bookingRequest.getUserName();
    }

    public String getAddress() {
        return bookingRequest.getAddress();
    }

    public String getTokenNumber() {
        return bookingRequest.getTokenNumber();
    }

    public String getTimeWindow() {
        return this.timeWindow;
    }

    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }

    public int getBookingId(){ return bookingRequest.getId(); }

    public String getRequestStatus() { return bookingRequest.getRequestStatus();}
}
