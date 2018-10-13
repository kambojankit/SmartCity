package com.tomtom.hackathon.smartcity.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/schedule")
public class SchedulingController {

    @GetMapping("/get")
    public ResponseEntity getSchedule(){
        return new ResponseEntity("list of all schedule is returned", HttpStatus.CREATED);
    }

}

