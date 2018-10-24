package com.tomtom.hackathon.smartcity.repositories;

import com.tomtom.hackathon.smartcity.models.DriverMappingDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<DriverMappingDetails,Long>{

    @Query("SELECT d FROM DriverMappingDetails d WHERE d.driverName = ?1")
    DriverMappingDetails findRegion(String driverName);
}
