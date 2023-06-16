package com.example.csharing.service;

import com.example.csharing.dao.NickNameRepository;
import com.example.csharing.domain.UserNickName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserNickNameService {
    @Autowired
    NickNameRepository nickNameRepository;
    @Transactional
    public UserNickName saveNickName(UserNickName userNickName){
        return nickNameRepository.save(userNickName);
    }
    @Transactional
    public UserNickName updateNickName(UserNickName userNickName){
        return nickNameRepository.save(userNickName);
    }
    @Transactional
    public void deleteNickName(Long id){
        nickNameRepository.deleteById(id);
    }
    @Transactional
    public UserNickName findNickNameById(Long id){
        return nickNameRepository.findById(id).orElse(null);
    }

}
