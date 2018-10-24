package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.RoutingRequest;

import java.util.List;

public class RoutingView {

    private RoutingRequest routingRequest;

    private String driverName;
    private List<String> listOfLatAndLong;

    public RoutingView(RoutingRequest routingRequest){ this.routingRequest = routingRequest;};


    public String getDriverName() {
        return routingRequest.getDriverName();
    }

    public List<String> getListOfLatAndLong() {
        return listOfLatAndLong;
    }
    public void setListOfLatAndLong(List<String> listOfLatAndLong){

        this.listOfLatAndLong = listOfLatAndLong;
    }
}
