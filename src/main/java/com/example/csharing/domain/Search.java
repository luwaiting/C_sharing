package com.example.csharing.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Search {
    @Id
    @GeneratedValue
    private Long id;
    private String keyword;

    @ManyToMany(mappedBy = "searches")
    private List<User> users=new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
