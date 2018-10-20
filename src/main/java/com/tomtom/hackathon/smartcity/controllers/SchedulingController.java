package com.tomtom.hackathon.smartcity.controllers;

import com.tomtom.hackathon.smartcity.models.SchedulingCreationRequest;
import com.tomtom.hackathon.smartcity.services.CreateScheduleService;
import com.tomtom.hackathon.smartcity.views.SchedulingRequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/schedule")
public class SchedulingController {

    @Autowired
    private CreateScheduleService createScheduleService;

    @PostMapping("/create")
    public ResponseEntity createSchedule(@Validated @RequestBody SchedulingCreationRequest schedulingCreationRequest){

        SchedulingRequestView schedulingRequestView = createScheduleService.createSchedule(schedulingCreationRequest);

        if(Objects.isNull(schedulingCreationRequest)){
            return new ResponseEntity("Couldn't fetch data from database", HttpStatus.EXPECTATION_FAILED);
        }
        else
            return new ResponseEntity(createScheduleService.createSchedule(schedulingCreationRequest), HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity getSchedule(){
        return new ResponseEntity(createScheduleService.getSchedulingData(), HttpStatus.CREATED);
    }

}

