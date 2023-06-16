package com.example.csharing.dao;

import com.example.csharing.domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRepository extends JpaRepository<Search,Long> {
    @Query("select count(s) from Search s where s.keyword=?1")
    Integer countByKeyword(String keyword);
}
