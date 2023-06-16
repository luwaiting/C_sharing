package com.example.csharing.service;

import com.example.csharing.dao.P_commentRepository;
import com.example.csharing.dao.ReportCommentRepository;
import com.example.csharing.dao.UserRepository;
import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.PostingComment;
import com.example.csharing.domain.report.ReportComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReportCommentService {
    @Autowired
    ReportCommentRepository reportCommentRepository;
    @Autowired
    P_commentRepository p_commentRepository;
    @Autowired
    UserRepository userRepository;
    @Transactional
    public ReportComment saveReportComment(Long accountId,Long commentId,String content){
        PostingComment postingComment=p_commentRepository.findById(commentId).orElse(null);
        User user=userRepository.findById(accountId).orElse(null);
        ReportComment reportComment=new ReportComment();
        reportComment.setPostingComment(postingComment);
        reportComment.setUser(user);
        reportComment.setContent(content);
        return reportCommentRepository.save(reportComment);
    }
}
