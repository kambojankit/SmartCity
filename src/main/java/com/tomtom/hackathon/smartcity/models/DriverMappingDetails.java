package com.tomtom.hackathon.smartcity.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "DRIVER_DETAILS")

public class DriverMappingDetails {
    @Id
    private String driverName;
    private String vehicleNumber;
    private String subRegion;
    private String city;
    private String state;
    private String zipCode;
}
