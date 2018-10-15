package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.UserCreationRequest;

public class UserCreationRequestView {

    private UserCreationRequest userCreationRequest;
    private String userName;

    public UserCreationRequestView(UserCreationRequest userCreationRequest){ this.userCreationRequest = userCreationRequest;};

    public String getUserName() {
        return this.userCreationRequest.getUserName() + " created!!!";
    }
}
