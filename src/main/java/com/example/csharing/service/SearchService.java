package com.example.csharing.service;

import com.example.csharing.dao.SearchRepository;
import com.example.csharing.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SearchService {
    @Autowired
    SearchRepository searchRepository;
    @Transactional
    public Integer countKeyword(String keyword){
        return searchRepository.countByKeyword(keyword);
    }
    @Transactional
    public Search save(Search search){
        return searchRepository.save(search);
    }



}
