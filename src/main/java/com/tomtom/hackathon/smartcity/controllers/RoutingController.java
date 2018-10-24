package com.tomtom.hackathon.smartcity.controllers;

import com.tomtom.hackathon.smartcity.services.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class RoutingController {

    @Autowired
    private RoutingService routingService;

    @GetMapping("/routes/")
    public ResponseEntity getroutes(@RequestParam("driverName") String driverName,
                                     @RequestParam("tokenNumber") String tokenNumber ){
        return new ResponseEntity(routingService.getRoutingRequest(driverName,tokenNumber), HttpStatus.CREATED);
    }
}

