package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.tasks.Task;
import com.jspiders.taskapi.data.tasks.TaskDTO;
import com.jspiders.taskapi.data.tasks.TaskRepository;
import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.errors.DuplicateUserException;
import com.jspiders.taskapi.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl2 implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final TaskRepository taskRepository;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {

        //Data verification
       boolean exists = appUserRepository.
               existsByEmailOrMobile(createUserRequest.getEmail(),
                createUserRequest.getMobile());

       if(exists == true)
       {
           throw new DuplicateUserException("User with given email/mobile already exists");
       }


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
    public ResponseEntity<List<AppUserDTO>> getAllUsers(Long userId) {
        log.info("this is getAllUsers()");
        //verify the user
       boolean isPresent = appUserRepository.existsById(userId);
       if(isPresent==false)
       {
           throw new IllegalArgumentException("Security ERROR : USERID is not VALID");
       }
        //BL
        //database ops (GET ALL USERS FROM DB)
        List<AppUser> appUserList = appUserRepository.findAll();
        List<AppUserDTO> appUserDTOList = new ArrayList<>();

        //business logics(REMOVE PASSWORD DATA FROM RESPONSE)
        //build the response
        for (AppUser appUser:appUserList) {
            AppUserDTO appUserDTO = mapper.convertValue(appUser, AppUserDTO.class);
            appUserDTOList.add(appUserDTO);
        }

        //return the response
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appUserDTOList);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserById(Long userId) {
        log.info("getUserById()");
        //find the user by userId
        AppUser appUser = appUserRepository.findById(userId).orElseThrow();

        //convert appuser to appuseDto
        AppUserDTO appUserDTO = mapper.convertValue(appUser, AppUserDTO.class);

        //find all the task of the user by userId
        List<Task> taskList = taskRepository.findByAppUserUserId(userId);
        List<TaskDTO> taskDtoList = new ArrayList<>();

        //convert task to taskdto
        for (Task task:taskList)
        {
           TaskDTO taskDTO = mapper.convertValue(task,TaskDTO.class);
           taskDtoList.add(taskDTO);
        }
        //set the taskDto list
        appUserDTO.setTaskList(taskDtoList);
        //return response object
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appUserDTO);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserByEmail(String email) {
       Optional<AppUser> optional = appUserRepository.findByEmail(email);
       AppUser appUser = optional.get();
       AppUserDTO appUserDTO = mapper.convertValue(appUser, AppUserDTO.class);
       log.info("appUserDTO {}",appUserDTO);
        return ResponseEntity.ok().body(appUserDTO);
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        log.info("inside login()");
        String userId;
        LoginResponse loginResponse;
        //check if user with email and password exists in DB
        boolean isPresent =  appUserRepository.existsByEmailAndPassword
                             (loginRequest.getEmail(), loginRequest.getPassword());

        if(isPresent==true)
        {
            //if user with email and password exists get userid
           Optional<AppUser> userOptional = appUserRepository.findByEmail(loginRequest.getEmail());
           AppUser appUser = userOptional.get();
           loginResponse = mapper.convertValue(appUser, LoginResponse.class);
           loginResponse.setMessage("Login success");
           //userId = String.valueOf(appUser.getUserId());
        }
        else {
            //if user with email and password DO NOT exists throw execption
            throw new IllegalArgumentException("Invalid Username / Password");
        }

        //return userId of the given user
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<String> updateUserEmail(Long userId, UpdateUserEmailRequest updateUserEmailRequest) {
        log.info("this is updateUserEmail()");
        //verify the user
        boolean isPresent = appUserRepository.existsById(userId);
        if(isPresent==false)
        {
            throw new IllegalArgumentException("Security ERROR : USERID is not VALID");
        }
        Optional<AppUser> appUserOptional =
                appUserRepository.findByEmailAndUserId(updateUserEmailRequest.getOldEmail()
                ,updateUserEmailRequest.getUserId());

        if(appUserOptional.isEmpty())
        {
            throw new IllegalArgumentException("User with given email and userid NOT FOUND");
        }
        else {
            AppUser appUser = appUserOptional.get();
            appUser.setEmail(updateUserEmailRequest.getNewEmail());
            appUserRepository.save(appUser);
        }

        return ResponseEntity.ok("User email updated successfully");
    }

}
