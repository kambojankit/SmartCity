package com.tomtom.hackathon.smartcity.models;

public enum  RequestStatus {

    CANCEL("CANCEL"),
    ACTIVE("ACTIVE"),
    COMPLETE("COMPLETE"),
    INPROCESS("INPROCESS");

    private String requestStatus;

     RequestStatus(String requestStatus){
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

}
