package com.tomtom.hackathon.smartcity.repositories;

import com.tomtom.hackathon.smartcity.models.DriverMappingDetails;
import com.tomtom.hackathon.smartcity.models.RoutingRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<DriverMappingDetails,Long>{

    @Query("SELECT d FROM DriverMappingDetails d WHERE d.emailId = ?1")
    DriverMappingDetails findRegion(String emailId);

    @Query("SELECT r FROM RoutingRequest r WHERE r.emailId = ?1")
    List<RoutingRequest> findRouteByDriverEmail(String emailId);
}
