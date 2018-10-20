package com.tomtom.hackathon.smartcity.repositories;

import com.tomtom.hackathon.smartcity.models.SchedulingCreationRequest;
import org.springframework.data.repository.CrudRepository;

public interface CreateScheduleRepository extends CrudRepository<SchedulingCreationRequest,Long>{
}
