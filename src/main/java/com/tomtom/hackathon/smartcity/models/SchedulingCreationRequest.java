package com.tomtom.hackathon.smartcity.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "SCHEDULING_INFO")

public class SchedulingCreationRequest {

    @Id
    private int id;
    private String userName;
    private String latAndLong;
    private String address;
    private ZonedDateTime timeWindow;
}
