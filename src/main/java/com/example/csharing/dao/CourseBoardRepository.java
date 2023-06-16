package com.example.csharing.dao;

import com.example.csharing.domain.CourseBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseBoardRepository extends JpaRepository<CourseBoard,Long> {

    @Query("select c from CourseBoard c where c.boardName like %?1%")
    List<CourseBoard> findByKeyword(String name);
    @Query("select c from CourseBoard c order by c.size desc")
    List<CourseBoard> findBySizeDesc();
}
