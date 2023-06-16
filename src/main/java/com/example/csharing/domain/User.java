package com.example.csharing.domain;


import com.example.csharing.domain.posting.Posting;
import com.example.csharing.domain.posting.PostingComment;
import com.example.csharing.domain.report.ReportComment;
import com.example.csharing.domain.report.ReportPosting;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    // id名稱都設為id就好-->名稱映射真的很麻煩
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int sex;
    private String email;
    private String school;
    private String introduction;
    private String password;
    private int identity;
    private int loveNumber;
    private int activated;
    public int tScore;
    public String displayedName;

//    @OneToOne(mappedBy = "user",cascade = CascadeType.PERSIST)
//    private Student student;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private List<Posting> posting=new ArrayList<Posting>();

    @ManyToMany
    private List<Posting>savePosting=new ArrayList<Posting>();
    @ManyToMany
    @JoinTable(name = "posting_interaction")
    private List<Posting>postingInteraction=new ArrayList<Posting>();
    @ManyToMany
    @JoinTable(name = "posting_comment_interaction")
    private List<PostingComment>postingCommentInteraction=new ArrayList<PostingComment>();
    @ManyToMany
    @JoinTable(name= "search_record")
    private List<Search>searches=new ArrayList<Search>();
    @ManyToMany
    @JoinTable(name = "follow_board")
    private List<CourseBoard>courseBoards=new ArrayList<CourseBoard>();

    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private List<PostingComment>comments=new ArrayList<PostingComment>();;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private List<UserNickName>nickNameList=new ArrayList<UserNickName>();

    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private List<ReportPosting>reportPostings=new ArrayList<ReportPosting>();
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private List<ReportComment>reportComments=new ArrayList<ReportComment>();

//    @OneToOne(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//    Teacher teacher;
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
    //如果利用userRepository新增修改的話使用-->利用多的那一方的Repository的話不用


    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public int gettScore() {
        return tScore;
    }

    public void settScore(int tScore) {
        this.tScore = tScore;
    }

    public int getLoveNumber() {
        return loveNumber;
    }

    public void setLoveNumber(int loveNumber) {
        this.loveNumber = loveNumber;
    }

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }
    public boolean activeOrNot(){
        if(this.activated==1){
            return true;
        }
        return false;
    }

    public List<CourseBoard> getCourseBoards() {
        return courseBoards;
    }

    public void setCourseBoards(List<CourseBoard> courseBoards) {
        this.courseBoards = courseBoards;
    }

    public List<Search> getSearches() {
        return searches;
    }

    public void setSearches(List<Search> searches) {
        this.searches = searches;
    }

    public List<ReportComment> getReportComments() {
        return reportComments;
    }

    public void setReportComments(List<ReportComment> reportComments) {
        this.reportComments = reportComments;
    }

    public List<ReportPosting> getReportPostings() {
        return reportPostings;
    }

    public void setReportPostings(List<ReportPosting> reportPostings) {
        this.reportPostings = reportPostings;
    }

    public List<PostingComment> getPostingCommentInteraction() {
        return postingCommentInteraction;
    }

    public void setPostingCommentInteraction(List<PostingComment> postingCommentInteraction) {
        this.postingCommentInteraction = postingCommentInteraction;
    }

    public List<Posting> getPostingInteraction() {
        return postingInteraction;
    }

    public void setPostingInteraction(List<Posting> postingInteraction) {
        this.postingInteraction = postingInteraction;
    }

    public List<UserNickName> getNickNameList() {
        return nickNameList;
    }

    public void setNickNameList(List<UserNickName> nickNameList) {
        this.nickNameList = nickNameList;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public List<Posting> getSavePosting() {
        return savePosting;
    }

    public void setSavePosting(List<Posting> savePosting) {
        this.savePosting = savePosting;
    }

    public void addComment(PostingComment comment){
        comment.setUser(this);
        comments.add(comment);
    }
    public void addPosting(Posting posting){
        posting.setUser(this);
        this.posting.add(posting);
    }

    public List<PostingComment> getComments() {
        return comments;
    }

    public void setComments(List<PostingComment> comments) {
        this.comments = comments;
    }

    public List<Posting> getPosting() {
        return posting;
    }

    public void setPosting(List<Posting> posting) {
        this.posting = posting;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //    public Long getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Long user_id) {
//        this.user_id = user_id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return sex == user.sex && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(nickname, user.nickname) && Objects.equals(email, user.email) && Objects.equals(school, user.school) && Objects.equals(introduction, user.introduction) && Objects.equals(password, user.password) && Objects.equals(student, user.student) && Objects.equals(posting, user.posting) && Objects.equals(savePosting, user.savePosting) && Objects.equals(comments, user.comments);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
