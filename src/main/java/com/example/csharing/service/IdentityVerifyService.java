package com.example.csharing.service;

import com.example.csharing.dao.IdentityVerifyRepository;
import com.example.csharing.domain.IdentityConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IdentityVerifyService {
    @Autowired
    IdentityVerifyRepository identityVerifyRepository;
    @Transactional
    public IdentityConfirm save(IdentityConfirm identityConfirm){
        return identityVerifyRepository.save(identityConfirm);
    }
}
