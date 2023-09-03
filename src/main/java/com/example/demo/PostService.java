package com.example.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PostService(PostRepository postRepository, RabbitTemplate rabbitTemplate) {
        this.postRepository = postRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void processPost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            // Implemente a lógica necessária para processar a postagem
            post.setState("POST_OK");
            postRepository.save(post);

            // Enviar uma mensagem para a fila para iniciar o processamento
            rabbitTemplate.convertAndSend("post-processing-queue", post.getId());
        }
    }

    public void disablePost(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setState("DISABLED");
            postRepository.save(post);
        }
    }

    @Service
    public class PostService {
        // ...

        public void disablePost(Long postId) {
            Post post = postRepository.findById(postId).orElse(null);
            if (post != null) {
                post.setState("DISABLED");
                postRepository.save(post);
            }
        }

        public void reprocessPost(Long postId) {
            // Implemente a lógica para reprocessar a postagem
        }

        // ...
    }
