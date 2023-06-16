package com.example.csharing.dao;

import com.example.csharing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
//    User findByEmail(String email);
    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);

}
