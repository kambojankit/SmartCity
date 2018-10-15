package com.tomtom.hackathon.smartcity.repositories;

import com.tomtom.hackathon.smartcity.models.UserCreationRequest;
import org.springframework.data.repository.CrudRepository;

public interface CreateUserRepository extends CrudRepository<UserCreationRequest, Long> {
}
