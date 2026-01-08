package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.users.AppUser;
import com.jspiders.taskapi.data.users.CreateUserRequest;
import com.jspiders.taskapi.services.AppUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController
{
    //immutable
    private final AppUserService appUserService;
    @Autowired
    public UserController(AppUserService appUserService)
    {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid CreateUserRequest createUserRequest)
    {
        log.info("inside addUser() createUserRequest : {}",createUserRequest);
        ResponseEntity<String> response = appUserService.createUser(createUserRequest);
        log.info("inside addUser() : User created");
        return response;
    }

    @PutMapping
    public ResponseEntity<String> updateUser(){
        log.info("updateUser()");
        System.out.println("this is UserController --> updateUser()");
        ResponseEntity<String> response = appUserService.updateUser();
        return response;
    }

    @DeleteMapping
    ResponseEntity<String> deleteUser(String email,String mobile,String password){
        System.out.println("this is UserController --> deleteUser()");
        ResponseEntity<String> response = appUserService.deleteUser(email,mobile,password);
        return response;
    }

    @GetMapping
    ResponseEntity<List<AppUser>> getAllUsers(){
        System.out.println("this is UserController --> getAllUsers()");
        ResponseEntity<List<AppUser>> response = appUserService.getAllUsers();
        return response;
    }
    @GetMapping("/{userId}")
    ResponseEntity<AppUser> getUserById(@PathVariable Long userId){
        System.out.println("this is UserController --> getUserById()");
        ResponseEntity<AppUser> response = appUserService.getUserById(userId);
        return response;
    }
}
