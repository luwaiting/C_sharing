package com.example.csharing.service;

import com.example.csharing.dao.CourseBoardRepository;
import com.example.csharing.dao.PostingRepository;
import com.example.csharing.domain.CourseBoard;
import com.example.csharing.domain.posting.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseBoardService {
    @Autowired
    CourseBoardRepository courseBoardRepository;

    @Autowired
    PostingRepository postingRepository;
    @Transactional
    public List<CourseBoard>findAll(){
        return courseBoardRepository.findAll();
    }

    @Transactional
    public CourseBoard addCourseBoard(CourseBoard courseBoard){
        return courseBoardRepository.save(courseBoard);
    }
    @Transactional
    public CourseBoard findCourseBoardById(Long id){
        return courseBoardRepository.findById(id).orElse(null);
    }
    @Transactional
    public List<CourseBoard> findCourseBoardByKeyword(String name){
        return courseBoardRepository.findByKeyword(name);
    }

    @Transactional
    public CourseBoard updateCourseBoard(CourseBoard courseBoard){
        return courseBoardRepository.save(courseBoard);
    }
    @Transactional
    public void deleteCourseBoard(Long id){
        courseBoardRepository.deleteById(id);
    }
    //board納入posting
    @Transactional
    public CourseBoard includePostingById(Long boardId,Long postingId){
        CourseBoard courseBoard= courseBoardRepository.findById(boardId).orElse(null);
        Posting posting=postingRepository.findById(postingId).orElse(null);
        courseBoard.getPostingList().add(posting);
        return courseBoard;
    }
    @Transactional
    public CourseBoard includePosting(Posting posting,CourseBoard courseBoard){
        courseBoard.getPostingList().add(posting);
        courseBoard.setSize(courseBoard.getSize()+1);
        return courseBoard;
    }
    @Transactional
    public CourseBoard unIncludePosting(Long boardId,Long postingId){
        CourseBoard courseBoard= courseBoardRepository.findById(boardId).orElse(null);
        Posting posting=postingRepository.findById(postingId).orElse(null);
        //if need to remove the posting side 依賴關西？
        courseBoard.getPostingList().remove(posting);
        return courseBoard;
    }
    @Transactional
    public List<CourseBoard>findByDescSize(){
        return courseBoardRepository.findBySizeDesc();
    }

}
