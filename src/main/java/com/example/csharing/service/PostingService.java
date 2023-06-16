package com.example.csharing.service;

import com.example.csharing.dao.PostingRepository;
import com.example.csharing.domain.posting.Posting;
import com.example.csharing.domain.posting.PostingComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostingService {
    @Autowired
    PostingRepository postingRepository;
    @Transactional
    public List<Posting> findAllByUserId(Long id){
        return postingRepository.findAllByUserId(id);
    }

    @Transactional
    public int findAllLikeNumber(Long i){
        return postingRepository.findTotalLikeNumber(i);
    }
    //no order
    @Transactional
    public List<Posting>findPostingByIdentity(int i){
        return postingRepository.findByIdentity(i);
    }
    @Transactional
    public List<Posting>findPostingByIdentityAndType(int i,int j){
        return postingRepository.findAllByIdentityAndCourseType(i,j);
    }
    //order
    @Transactional
    public List<Posting> findAllByDescTime(int i){
        return postingRepository.findAllByIdentityOrderByTime(i);
    }
    @Transactional
    public List<Posting>findAllByDescLike(int i){
        return postingRepository.findAllByIdentityOrderByLike(i);
    }
    @Transactional
    public List<Posting>findByCourseTypeOrderByTime(int i,int j){
        return postingRepository.findAllByIdentityAndCourseTypeOrderByTime(i,j);
    }
    @Transactional
    public List<Posting>findByCourseTypeOrderByLike(int i,int j){
        return postingRepository.findAllByIdentityAndCourseTypeOrderByLike(i,j);
    }

    @Transactional
    public Posting addPosting(Posting posting){
        return postingRepository.save(posting);
    }
    @Transactional
    public Posting updatePosting(Posting posting){
        return postingRepository.save(posting);
    }
    @Transactional
    public Posting findPosting(Long id){
        return postingRepository.findById(id).orElse(null);
    }
    @Transactional
    public void deletePosting(Long id){
        postingRepository.deleteById(id);
    }





}
