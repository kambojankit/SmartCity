package com.tomtom.hackathon.smartcity.services;

import com.tomtom.hackathon.smartcity.models.DriverMappingDetails;
import com.tomtom.hackathon.smartcity.repositories.DriverRepository;
import com.tomtom.hackathon.smartcity.views.BookingHistoryView;
import com.tomtom.hackathon.smartcity.views.DriverEntityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    /**
     * it gonna create Entry for Driver into DB. Kind of registration process for drivers to use SmartCity-App.
     * @param driverMappingDetails
     * @return
     */
    public DriverEntityView saveDriverEntry(DriverMappingDetails driverMappingDetails){

       return new DriverEntityView(driverRepository.save(driverMappingDetails));

    }

    /**
     * return all the routes which have been processed by drivers.
     * @return
     */
    public List<DriverEntityView> findAllRoutes(){

        // TODO need to write the logic for finding route history.

        List<DriverEntityView> listOfRoutes = new ArrayList<>();

        driverRepository.
                findAll().
                forEach(e ->listOfRoutes.add(new DriverEntityView(e)));

        return listOfRoutes;
    }

    /**
     * return the list are Drivers which are registered with SmartCity App.
     * @return
     */
    public List<DriverEntityView> findAllDrivers(){

        List<DriverEntityView> listOfDrivers = new ArrayList<>();

        driverRepository.
                findAll().
                forEach(e ->listOfDrivers.add(new DriverEntityView(e)));

        return listOfDrivers;
    }
}
