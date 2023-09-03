package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExternalApiService {

    private final String apiUrl = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private PostRepository postRepository; // Injetar o repositório de postagens

    // Enumeração para representar o estado de uma postagem
    public enum PostState {
        CREATED,
        POST_OK, DISABLED
    }

    @Async
    public void retrieveAndProcessPosts() {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Post[] posts = objectMapper.readValue(json, Post[].class);

            for (Post post : posts) {
                String commentsUrl = apiUrl + "/" + post.getId() + "/comments";
                Comment[] comments = restTemplate.getForObject(commentsUrl, Comment[].class);

                for (Comment comment : comments) {
                    comment.setPost(post);
                    post.getComments().add(comment);
                }

                post.setState1(PostState.POST_OK);
                postRepository.save(post); // Salvar a postagem no repositório
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
