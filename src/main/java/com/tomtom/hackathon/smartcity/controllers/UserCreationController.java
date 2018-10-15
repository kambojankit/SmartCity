package com.tomtom.hackathon.smartcity.controllers;

import com.tomtom.hackathon.smartcity.models.UserCreationRequest;
import com.tomtom.hackathon.smartcity.services.CreateUserService;
import com.tomtom.hackathon.smartcity.views.UserCreationRequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserCreationController {

    @Autowired
    private CreateUserService createUserService;

    @PostMapping("/create")
    public ResponseEntity createUser(@Validated @RequestBody UserCreationRequest userCreationRequest){

        UserCreationRequestView userCreationRequestView = createUserService.createUser(userCreationRequest);
        if (Objects.isNull(userCreationRequestView)) {
            return new ResponseEntity("Regristration process failed.", HttpStatus.EXPECTATION_FAILED);
        }
        else
            return new ResponseEntity(userCreationRequestView, HttpStatus.CREATED);
    }
}
