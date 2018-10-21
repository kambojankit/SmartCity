package com.tomtom.hackathon.smartcity.controllers;

import com.tomtom.hackathon.smartcity.models.BookingRequest;
import com.tomtom.hackathon.smartcity.services.BookingService;
import com.tomtom.hackathon.smartcity.views.BookingRequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity bookingCreation(@Validated @RequestBody BookingRequest bookingRequest){

        BookingRequestView bookingRequestView = bookingService.createBooking(bookingRequest);

        if(Objects.isNull(bookingRequest)){
            return new ResponseEntity("Couldn't fetch data from database", HttpStatus.EXPECTATION_FAILED);
        }
        else
            return new ResponseEntity(bookingService.createBooking(bookingRequest), HttpStatus.CREATED);
    }
    @GetMapping("/get/history")
    public ResponseEntity getBooking(){
        return new ResponseEntity(bookingService.getBookingHistory(), HttpStatus.CREATED);
    }

    @PutMapping("/cancel/request")
    public ResponseEntity updateBooking(@Validated @RequestBody BookingRequest bookingRequest){
        return new ResponseEntity(bookingService.cancelUserRequest(bookingRequest.getId()),HttpStatus.CREATED);
    }

}

