package com.jspiders.taskapi.data.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appuser")
public class AppUser2
{
   @Id
   private Long id;
   @Column(name = "name",nullable = false)
   private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;
}
