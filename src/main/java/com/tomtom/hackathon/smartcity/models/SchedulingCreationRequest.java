package com.tomtom.hackathon.smartcity.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "SCHEDULING_INFO")

public class SchedulingCreationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String latAndLong;
    private String address;
    private String tokenNumber;
}
