package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id
    private Long id;
    private String title;
    private String body;

    // Enumeração representando os estados da postagem
    public enum PostState {
        CREATED,
        POST_OK,
        DISABLED
    }

    @Enumerated(EnumType.STRING)
    private PostState state;  // Campo para armazenar o estado da postagem

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.state = PostState.CREATED; // Definindo o estado inicial como CREATED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public PostState getState() {
        return state;
    }

    public void transitionToPostOk() {
        if (this.state == PostState.CREATED) {
            this.state = PostState.POST_OK;
        } else {
            throw new IllegalStateException("Transição de estado para POST_OK não permitida.");
        }
    }

    public void transitionToDisabled() {
        if (this.state == PostState.POST_OK) {
            this.state = PostState.DISABLED;
        } else {
            throw new IllegalStateException("Transição de estado para DISABLED não permitida.");
        }
    }

	public void setState1(com.example.demo.ExternalApiService.PostState postOk) {
		// TODO Auto-generated method stub
		
	}

	public void setState1(String string) {
		// TODO Auto-generated method stub
		
	}
}
