package com.tomtom.hackathon.smartcity.services;

import com.tomtom.hackathon.smartcity.models.BookingRequest;
import com.tomtom.hackathon.smartcity.models.DriverMappingDetails;
import com.tomtom.hackathon.smartcity.models.RoutingRequest;
import com.tomtom.hackathon.smartcity.repositories.BookingRepository;
import com.tomtom.hackathon.smartcity.repositories.DriverRepository;
import com.tomtom.hackathon.smartcity.repositories.RoutingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoutingService {

    @Autowired
    private RoutingRepository routingRepository;

    @Autowired
    private DriverRepository driverRepository;


    @Autowired
    private BookingRepository bookingRepository;


    @Value("${date.format}")
    private String dateFormat;

    public List<String> getRoutingRequest(String driverName,String tokenNumber){

        List<BookingRequest> listOfBooking = null;
        List<String> listOfLatAndLong = null;
        //TODO : how to create model object. is it a good way to create it using new keyword ?

        RoutingRequest routingRequest = new RoutingRequest();
        DriverMappingDetails driverMappingDetails = driverRepository.findRegion(driverName);

        listOfBooking = bookingRepository.findBookingByRegionAndByToken(
                        tokenNumber,
                        driverMappingDetails.getSubRegion(),
                        this.getCurrentDate());

        listOfLatAndLong = this.getListOfLatAndLong(listOfBooking);

        routingRepository.save(
                              this.setRoutingParam(
                              driverName,
                              tokenNumber,
                              routingRequest,
                              this.converListToString(listOfLatAndLong)
                              )
        );
        return listOfLatAndLong;
    }

    public String getCurrentDate(){

        DateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        return dateFormat.format(new Date());
    }

    public List<String> getListOfLatAndLong(List<BookingRequest> listOfBooking){

        List<String> listOfLatAndLong = new ArrayList<>();
        for (BookingRequest bookingRequest:listOfBooking) {
           listOfLatAndLong.add(bookingRequest.getLatAndLong());
        }
        return listOfLatAndLong;
    }

    public String converListToString(List<String> listOfLatAndLong){
      return   String.join(",",listOfLatAndLong);
    }

    public RoutingRequest setRoutingParam(String driverName,String tokenNumber,
                                            RoutingRequest routingRequest,String wayPoints){

        routingRequest.setDriverName(driverName);
        routingRequest.setTokenNumber(tokenNumber);
        routingRequest.setWayPoints(wayPoints);
        routingRequest.setDateOfPickup(this.getCurrentDate());
        return routingRequest;
    }
}
