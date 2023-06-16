package com.example.csharing.dao;

import com.example.csharing.domain.IdentityConfirm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityVerifyRepository extends JpaRepository<IdentityConfirm,Long> {

}
