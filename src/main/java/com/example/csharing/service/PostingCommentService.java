package com.example.csharing.service;

import com.example.csharing.dao.P_commentRepository;
import com.example.csharing.domain.posting.PostingComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostingCommentService {
    @Autowired
    P_commentRepository p_commentRepository;
    @Transactional
    public int findAllLike(Long userId){
        return p_commentRepository.findAllCommentLike(userId);
    }
    @Transactional
    public List<PostingComment> findCommentOrderByPopular(Long i){
        return p_commentRepository.findAllByPostOrderByPopular(i);
    }
    @Transactional
    public List<PostingComment> findCommentOrderByTime(Long i){
        return p_commentRepository.findAllByPostOrderByTime(i);
    }
    @Transactional
    public PostingComment addComment(PostingComment postingComment){
        return p_commentRepository.save(postingComment);
    }
    @Transactional
    public PostingComment updateComment(PostingComment postingComment){
        return p_commentRepository.save(postingComment);
    }
    @Transactional
    public PostingComment findComment(Long id){
        return p_commentRepository.findById(id).orElse(null);
    }
    @Transactional
    public void deleteComment(Long id){
        p_commentRepository.deleteById(id);
    }
}
