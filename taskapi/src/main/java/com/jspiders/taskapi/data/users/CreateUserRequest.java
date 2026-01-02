package com.jspiders.taskapi.data.users;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String mobile;
    private String password;
}
