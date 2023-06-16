package com.example.csharing.dao;

import com.example.csharing.domain.UserNickName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NickNameRepository extends JpaRepository<UserNickName,Long> {
}
