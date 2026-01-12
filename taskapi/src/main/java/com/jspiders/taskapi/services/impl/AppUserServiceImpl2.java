package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl2 implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {

        //Convert request to ENTITY
        AppUser appUser = mapper.convertValue(createUserRequest, AppUser.class);

        //Set the required values depeneding on the requirement
        appUser.setActive(true);

       //Save the entity to the Database and get the stored data
       AppUser appUserInDb = appUserRepository.save(appUser);

      //Get required values for the table
       long userId = appUserInDb.getUserId();

       //build the response object
       CreateUserResponse response = new CreateUserResponse();
       response.setUserId(userId);
       response.setMessage("User Created");

       //return the response object with ResponseEntity
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> updateUser() {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteUser(String email, String mobile, String password) {
        return null;
    }

    @Override
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {
        return null;
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserById(Long userId) {
        return null;
    }
}
