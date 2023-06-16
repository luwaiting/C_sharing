package com.example.csharing.domain.report;

import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.Posting;

import javax.persistence.*;

@Entity
public class ReportPosting {
    @Id
    @GeneratedValue
    private Long id;
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},optional = false)
    private User user;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},optional = false)
    private Posting posting;

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
