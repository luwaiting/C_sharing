package com.example.csharing.domain;

import com.example.csharing.domain.posting.Posting;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class CourseBoard {
    @Id
    @GeneratedValue
    private Long id;
    private String boardName;
    private int size;
    private int courseType;
//    @OneToMany(mappedBy = "courseBoard",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @ManyToMany
    private List<Posting> postingList=new ArrayList<Posting>();
    //follow
    @ManyToMany(mappedBy = "courseBoards")
    private List<User>users=new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Posting> getPostingList() {
        return postingList;
    }

    public void setPostingList(List<Posting> postingList) {
        this.postingList = postingList;
    }

    public int getSize() {
        return size;
    }
    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

}
