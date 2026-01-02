package com.jspiders.taskapi.data.users;

import lombok.Data;

@Data
public class AppUser {
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private boolean isActive;
}
