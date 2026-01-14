package com.jspiders.taskapi.data.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    boolean existsByEmailOrMobile(String email,String mobile);
    boolean existsByMobile(String mobile);
    AppUser findByName(String name);
    AppUser findByMobile(String mobile);
    boolean existsByEmailAndPassword(String email,String password);
    boolean existsByMobileAndPassword(String mobile,String password);
}







