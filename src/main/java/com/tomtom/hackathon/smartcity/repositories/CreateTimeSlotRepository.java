package com.tomtom.hackathon.smartcity.repositories;

import com.tomtom.hackathon.smartcity.models.TimeWindowRequest;
import org.springframework.data.repository.CrudRepository;

public interface CreateTimeSlotRepository extends CrudRepository<TimeWindowRequest,Long>{
}
