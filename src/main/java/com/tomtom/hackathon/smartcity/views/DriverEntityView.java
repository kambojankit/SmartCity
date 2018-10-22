package com.tomtom.hackathon.smartcity.views;

import com.tomtom.hackathon.smartcity.models.DriverMappingDetails;

public class DriverEntityView {

    DriverMappingDetails driverMappingDetails;
    private String driverName;
    private String vehicleNumber;
    private String subRegion;

    public DriverEntityView(DriverMappingDetails driverMappingDetails){
        this.driverMappingDetails = driverMappingDetails;
    }

    public String getDriverName() {
        return this.driverMappingDetails.getDriverName();
    }

    public String getSubRegion() {
        return this.driverMappingDetails.getSubRegion();
    }

    public String getVehicleNumber() {
        return this.driverMappingDetails.getVehicleNumber();
    }
}
