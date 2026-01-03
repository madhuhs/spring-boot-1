package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.users.CreateUserRequest;
import com.jspiders.taskapi.services.AppUserService;
import com.jspiders.taskapi.services.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
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
    public ResponseEntity<String> addUser(@RequestBody CreateUserRequest createUserRequest)
    {
        System.out.println("this is UserController --> addUser()");
        ResponseEntity<String> response = appUserService.createUser(createUserRequest);
        return response;
    }
}
