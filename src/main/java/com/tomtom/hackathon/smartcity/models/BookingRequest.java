package com.tomtom.hackathon.smartcity.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "BOOKING_DETAILS")

public class BookingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String email;
    private String mobileNumber;
    private String latAndLong;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String tokenNumber;
    private String requestStatus;
}
