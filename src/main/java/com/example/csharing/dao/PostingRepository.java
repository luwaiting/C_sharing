package com.example.csharing.dao;

import com.example.csharing.domain.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting,Long> {
    @Query("select SUM(p.loveNumber) from Posting p inner join p.user user where user.id=?1")
    int findTotalLikeNumber(Long i);
    @Query("select p from Posting p inner join p.user user where user.id=?1")
    List<Posting>findAllByUserId(Long id);

    //no order
    @Query("select p from Posting p inner join p.user user where user.identity=?1 and p.courseType=?2")
    List<Posting>findAllByIdentityAndCourseType(int i,int j);
//    @Query("select p from Posting p inner join p.user user where user.identity=?1 order by p.postDate desc")
//    List<Posting>findByPostDate(int i);

    @Query("select p from Posting p inner join p.user user where user.identity=?1")
    List<Posting>findByIdentity(int i);

    //total post order by ?
    @Query("select p from Posting p inner join p.user user where user.identity=?1 order by p.postDate desc")
    List<Posting>findAllByIdentityOrderByTime(int i);

    @Query("select p from Posting p inner join p.user user where user.identity=?1 order by p.loveNumber desc")
    List<Posting>findAllByIdentityOrderByLike(int i);

    // required and general post order by?
    @Query("select p from Posting p inner join p.user user where user.identity=?1 and p.courseType=?2 order by p.postDate desc")
    List<Posting>findAllByIdentityAndCourseTypeOrderByTime(int i,int j);

    @Query("select p from Posting p inner join p.user user where user.identity=?1 and p.courseType=?2 order by p.loveNumber desc")
    List<Posting>findAllByIdentityAndCourseTypeOrderByLike(int i,int j);
}
