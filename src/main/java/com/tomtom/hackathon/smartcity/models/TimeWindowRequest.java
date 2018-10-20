package com.tomtom.hackathon.smartcity.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TIME_WINDOW_INFO")
public class TimeWindowRequest {
    @Id
    private String tokenNumber;
    private String timeWindow;
}
