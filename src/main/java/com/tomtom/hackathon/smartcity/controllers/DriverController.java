package com.tomtom.hackathon.smartcity.controllers;

import com.tomtom.hackathon.smartcity.models.BookingRequest;
import com.tomtom.hackathon.smartcity.models.DriverMappingDetails;
import com.tomtom.hackathon.smartcity.services.DriverService;
import com.tomtom.hackathon.smartcity.views.BookingRequestView;
import com.tomtom.hackathon.smartcity.views.DriverEntityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("/create")
    public ResponseEntity saveDriverData(@Validated @RequestBody DriverMappingDetails driverMappingDetails){

        DriverEntityView driverEntityView = driverService.saveDriverEntry(driverMappingDetails);

        if(Objects.isNull(driverEntityView)){
            return new ResponseEntity("unable to persist driver data", HttpStatus.EXPECTATION_FAILED);
        }
        else
            return new ResponseEntity(driverEntityView, HttpStatus.CREATED);
    }

    @GetMapping("/get/route/history")
    public ResponseEntity getRouteHistory(){
        return new ResponseEntity(driverService.findAllRoutes(), HttpStatus.CREATED);
    }

    @GetMapping("/get/drivers")
    public ResponseEntity getDrivers(){
        return new ResponseEntity(driverService.findAllDrivers(), HttpStatus.CREATED);
    }
}
