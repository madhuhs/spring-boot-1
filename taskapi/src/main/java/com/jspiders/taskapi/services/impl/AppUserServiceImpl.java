package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.users.AppUser;
import com.jspiders.taskapi.data.users.CreateUserRequest;
import com.jspiders.taskapi.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class AppUserServiceImpl implements AppUserService
{
    @Override
    public ResponseEntity<String> createUser(CreateUserRequest createUserRequest)
    {
        System.out.println("this is AppUserServiceImpl --> createUser()");

        //logics

        return null;
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
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return null;
    }

    @Override
    public ResponseEntity<AppUser> getUserById(Long userId) {
        return null;
    }
}
