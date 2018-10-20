package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.SchedulingCreationRequest;
import com.tomtom.hackathon.smartcity.models.TimeWindowRequest;

import java.time.ZonedDateTime;

public class SchedulingRequestView {

    private SchedulingCreationRequest schedulingCreationRequest;
    private TimeWindowRequest timeWindowRequest;
    private String userName;
    private String address;
    private String tokenNumber;
    private String latAndLong;
    private String timeWindow;


    public SchedulingRequestView(SchedulingCreationRequest schedulingCreationRequest){ this.schedulingCreationRequest = schedulingCreationRequest;}

    public String getUserName(){
        return schedulingCreationRequest.getUserName();
    }

    public String getAddress() {
        return schedulingCreationRequest.getAddress();
    }

    public String getTokenNumber() {
        return schedulingCreationRequest.getTokenNumber();
    }

    public String getLatAndLong() {
        return schedulingCreationRequest.getLatAndLong();
    }

    public String getTimeWindow() {
        return this.timeWindow;
    }

    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }
}
