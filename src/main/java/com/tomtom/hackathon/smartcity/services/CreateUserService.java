package com.tomtom.hackathon.smartcity.services;

import com.tomtom.hackathon.smartcity.models.UserCreationRequest;
import com.tomtom.hackathon.smartcity.repositories.CreateUserRepository;
import com.tomtom.hackathon.smartcity.views.UserCreationRequestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    @Autowired
    private CreateUserRepository createUserRepository;

    public UserCreationRequestView createUser(UserCreationRequest userCreationRequest) {
        return new UserCreationRequestView(createUserRepository.save(userCreationRequest));
    }
}
