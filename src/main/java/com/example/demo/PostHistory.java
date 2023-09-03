package com.example.demo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostHistory() {
        this.date = new Date();
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
