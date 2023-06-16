package com.example.csharing.domain.posting;

import com.example.csharing.domain.CourseBoard;
import com.example.csharing.domain.User;
import com.example.csharing.domain.report.ReportPosting;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Posting {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String courseName;
    @NotBlank
    private String teacherName;
    private int courseType;
    @NotBlank
    private String description;
    @NotNull
    private int sweet;
    @NotNull
    private int cool;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    private String nickname;
    private int loveNumber;
    private int commentNumber;

    @OneToMany(mappedBy = "posting",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<PostingComment> postingComment=new ArrayList<PostingComment>();
    @OneToMany(mappedBy = "posting",cascade = CascadeType.ALL)
    private List<ReportPosting>reportPostings=new ArrayList<ReportPosting>();
    // delete-->注意多那一方的
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},optional = false)
    private User user;
    @ManyToMany(mappedBy = "savePosting")
    private List<User>users=new ArrayList<User>();
    @ManyToMany(mappedBy = "postingInteraction")
    private List<User>interactionList=new ArrayList<User>();
    @ManyToMany(mappedBy = "postingList")
    private List<CourseBoard>courseBoard=new ArrayList<CourseBoard>();

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public List<ReportPosting> getReportPostings() {
        return reportPostings;
    }

    public int getLoveNumber() {
        return loveNumber;
    }

    public void setLoveNumber(int loveNumber) {
        this.loveNumber = loveNumber;
    }

    public void setReportPostings(List<ReportPosting> reportPostings) {
        this.reportPostings = reportPostings;
    }

    public List<User> getInteractionList() {
        return interactionList;
    }

    public void setInteractionList(List<User> interactionList) {
        this.interactionList = interactionList;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<CourseBoard> getCourseBoard() {
        return courseBoard;
    }

    public void setCourseBoard(List<CourseBoard> courseBoard) {
        this.courseBoard = courseBoard;
    }

    public List<PostingComment> getPostingComment() {
        return postingComment;
    }

    public void setPostingComment(List<PostingComment> postingComment) {
        this.postingComment = postingComment;
    }

    public void clearPosting(){
        user.getPosting().remove(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSweet() {
        return sweet;
    }

    public void setSweet(int sweet) {
        this.sweet = sweet;
    }

    public int getCool() {
        return cool;
    }

    public void setCool(int cool) {
        this.cool = cool;
    }
}
