package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Optional<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findById(postId);
    }
    
    public Comment[] getCommentsFromExternalApi1(Long postId) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + postId + "/comments";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Comment[].class);
    }

    public Comment addCommentToPost(Long postId, Comment newComment) {
        // Here you can add additional logic if needed before saving the comment
        newComment.setId(postId);
        return commentRepository.save(newComment);
    }

	public Comment[] getCommentsFromExternalApi(Long id) {
		// 
		return null;
	}
}
