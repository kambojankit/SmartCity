package com.tomtom.hackathon.smartcity.repositories;

import com.tomtom.hackathon.smartcity.models.DriverMappingDetails;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends CrudRepository<DriverMappingDetails,Long>{
}
