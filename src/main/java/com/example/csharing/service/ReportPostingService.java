package com.example.csharing.service;

import com.example.csharing.dao.PostingRepository;
import com.example.csharing.dao.ReportPostingRepository;
import com.example.csharing.dao.UserRepository;
import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.Posting;
import com.example.csharing.domain.report.ReportPosting;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReportPostingService {
    @Autowired
    ReportPostingRepository reportPostingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostingRepository postingRepository;
    @Transactional
    public ReportPosting saveReport(Long accountId,Long postingId,String content){
        User user=userRepository.findById(accountId).orElse(null);
        Posting posting=postingRepository.findById(postingId).orElse(null);
        ReportPosting reportPosting=new ReportPosting();
        reportPosting.setPosting(posting);
        reportPosting.setUser(user);
        reportPosting.setContent(content);
        return reportPostingRepository.save(reportPosting);
    }
}
