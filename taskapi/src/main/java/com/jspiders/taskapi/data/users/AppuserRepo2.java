package com.jspiders.taskapi.data.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppuserRepo2 extends JpaRepository<AppUser,Long> {

   // select * from appusers where mob = ? or email = ?
   boolean existsByEmailOrMobile(String email,String mobile);
   boolean existsByMobile(String mobile);
   boolean existsByEmailAndPassword(String email,String password);
}
