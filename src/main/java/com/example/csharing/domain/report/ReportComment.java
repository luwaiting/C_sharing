package com.example.csharing.domain.report;

import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.PostingComment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class ReportComment {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @ManyToOne(cascade = CascadeType.PERSIST,optional = false)
    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST,optional = false)
    private PostingComment postingComment;


    public PostingComment getPostingComment() {
        return postingComment;
    }

    public void setPostingComment(PostingComment postingComment) {
        this.postingComment = postingComment;
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
