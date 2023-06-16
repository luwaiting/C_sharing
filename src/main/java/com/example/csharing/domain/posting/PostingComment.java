package com.example.csharing.domain.posting;

import com.example.csharing.domain.User;
import com.example.csharing.domain.report.ReportComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PostingComment {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private int loveNumber;
    private String nickname;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},optional = false)
    private User user;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},optional = false)
    private Posting posting;
    //直接計算有多哥資料得知愛心數
    @ManyToMany(mappedBy = "postingCommentInteraction")
    private List<User>commentInteraction=new ArrayList<User>();

    @OneToMany(mappedBy = "postingComment",cascade = CascadeType.ALL)
    private List<ReportComment>reportComments=new ArrayList<ReportComment>();

    public int getLoveNumber() {
        return loveNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setLoveNumber(int loveNumber) {
        this.loveNumber = loveNumber;
    }

    public List<ReportComment> getReportComments() {
        return reportComments;
    }

    public void setReportComments(List<ReportComment> reportComments) {
        this.reportComments = reportComments;
    }

    public List<User> getCommentInteraction() {
        return commentInteraction;
    }

    public void setCommentInteraction(List<User> commentInteraction) {
        this.commentInteraction = commentInteraction;
    }

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
