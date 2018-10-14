package com.tomtom.hackathon.smartcity.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "USER_DETAILS")

public class UserCreationRequest {

    @Id
    private String userName;
    private String emailId;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
