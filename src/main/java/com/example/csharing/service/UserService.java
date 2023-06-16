package com.example.csharing.service;

import com.example.csharing.dao.CourseBoardRepository;
import com.example.csharing.dao.PostingRepository;
import com.example.csharing.dao.UserRepository;
import com.example.csharing.domain.CourseBoard;
import com.example.csharing.domain.Search;
import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostingRepository postingRepository;
    @Autowired
    CourseBoardRepository courseBoardRepository;
    @Autowired
    JavaMailSender mailSender;
//    @Transactional
//    public void register(Long accountId){
//        User user=userRepository.findById(accountId).orElse(null);
//
//        if()
//
//    }
    @Transactional
    public String sendEmail(Long accountId){
        User user=userRepository.findById(accountId).orElse(null);
        SimpleMailMessage message=null;
        if(user!=null){
            message = new SimpleMailMessage();
            message.setFrom("110306003@g.nccu.edu.tw");
            message.setTo(user.getEmail());
            message.setSubject("Verify");
            String token ="";
            for(int i=0;i<6;i++){
                int num = 0 + (int)(Math.random() * 9);
                token+=String.valueOf(num);
            }
            message.setText(token);
//            JavaMailSender mailSender=new JavaMailSenderImpl();
            mailSender.send(message);
            return token;
        }else{
            System.out.println("email error");
        }
        return "null";
    }
    @Transactional
    public User followBoard(Long userId,Long boardId){
        User user=userRepository.findById(userId).orElse(null);
        CourseBoard courseBoard=courseBoardRepository.findById(boardId).orElse(null);
        user.getCourseBoards().add(courseBoard);
        return user;
    }
    @Transactional
    public User savePosting(Long userId,Long postingId){
        User user=userRepository.findById(userId).orElse(null);
        Posting posting=postingRepository.findById(postingId).orElse(null);
        user.getSavePosting().add(posting);
        System.out.println("success");
        return user;
    }
    @Transactional
    public User unSavePosting(Long userId,Long postingId){
        User user=userRepository.findById(userId).orElse(null);
        Posting posting=postingRepository.findById(postingId).orElse(null);
        user.getSavePosting().remove(posting);
        return user;
    }
    @Transactional
    public User saveSearch(Long userId, Search search){
        User user=userRepository.findById(userId).orElse(null);
        user.getSearches().add(search);
        return user;
    }


    @Transactional
    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }
    @Transactional
    public User updateUser(User user){
        return userRepository.save(user);
    }
    @Transactional
    public void updateUser(Long id){
        userRepository.deleteById(id);
    }
    @Transactional
    public User findByEmail(String email){
        User user=userRepository.findByEmail(email);
//        System.out.println(user.getName());
        return user;
    }



}
