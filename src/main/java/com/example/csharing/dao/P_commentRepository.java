package com.example.csharing.dao;

import com.example.csharing.domain.posting.PostingComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface P_commentRepository extends JpaRepository<PostingComment,Long> {
    @Query("select sum(c.loveNumber) from PostingComment c inner join c.user user where user.id=?1")
    int findAllCommentLike(Long i);
    @Query("select c from PostingComment c inner join c.posting p where p.id=?1 order by c.loveNumber desc ")
    List<PostingComment>findAllByPostOrderByPopular(Long i);
    @Query("select c from PostingComment c inner join c.posting p where p.id=?1 order by c.postDate desc ")
    List<PostingComment>findAllByPostOrderByTime(Long i);

}
