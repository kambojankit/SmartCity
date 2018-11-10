package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.RoutingRequest;

import java.util.List;

public class DriverRoutingView {
    private RoutingRequest routingRequest;

    private String driverName;
    private String listOfWayPoints;
    private String emailId;


    public DriverRoutingView(RoutingRequest routingRequest){ this.routingRequest = routingRequest;};

    public String getEmailId() {
        return routingRequest.getEmailId();
    }

    public String getDriverName() {
        return routingRequest.getDriverName();
    }

    public String getListOfWayPoints() {
        return routingRequest.getWayPoints();
    }
}
