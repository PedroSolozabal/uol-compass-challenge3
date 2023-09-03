package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Services {
    private Map<Long, Post> posts = new HashMap<>();

    // Processa um post com base no ID fornecido
    public void processPost(Long postId) {
    	// Verifica se o postId está dentro do intervalo e se o post não existe no mapa
        if (postId >= 1 && postId <= 100 && !posts.containsKey(postId)) {
            // Cria um novo post com o ID e o adiciona ao mapa de posts
        	Post newPost = new Post(postId);
            posts.put(postId, newPost);
            System.out.println("Post processed with ID: " + postId);
        }
    }
    // Desativa um post com base no ID fornecido
    public void disablePost(Long postId) {
        Post post = posts.get(postId);
        if (post != null && post.getState().equals("ENABLED")) {
        	// Altera o estado do post para "DISABLED" se estiver habilitado
        	post.setState("DISABLED");
            System.out.println("Post with ID " + postId + " disabled.");
        }
    }
    // Reprocessa um post com base no ID fornecido
    public void reprocessPost(Long postId) {
        Post post = posts.get(postId);
        if (post != null && (post.getState().equals("ENABLED") || post.getState().equals("DISABLED"))) {
        	// Realiza o reprocessamento se o post estiver nos estados "ENABLED" ou "DISABLED"
        	System.out.println("Reprocessing post with ID: " + postId);
        }
    }

    // Consulta e retorna todos os posts como uma lista
    public List<Post> queryPosts() {
        return new ArrayList<>(posts.values());
    }
    // Classe interna que representa um post
    public class Post {
        private Long id;
        private String title;
        private String body;
        private List<Comment> comments;
        private List<HistoryEntry> history;

        // Construtor para criar um novo post com um ID
        public Post(Long id) {
            this.id = id;
            this.title = "";
            this.body = "";
            this.comments = new ArrayList<>();
            this.history = new ArrayList<>();
        }

     // Define o estado do post
        public void setState(String string) {
        	// TODO: Implemente a lógica para definir o estado do post
			
		}

        // Adiciona um comentário ao post
		public void addComment(Comment comment) {
            comments.add(comment);
        }

		// Adiciona uma entrada de histórico ao post
        public void addHistoryEntry(HistoryEntry historyEntry) {
            history.add(historyEntry);
        }

        public Long getId() {
            return id;
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

        public List<HistoryEntry> getHistory() {
            return history;
        }
        
        // Método para obter o estado atual do post
        public String getState() {
        	// Se o histórico estiver vazio, o estado é "NEW", caso contrário, obtém o estado da última entrada de histórico
        	if (history.isEmpty()) {
                return "NEW";
            }
            return history.get(history.size() - 1).getStatus();
        }

        // Getters and setters for all fields
        // ...
    }
    // Classe interna que representa um comentário
    public class Comment {
    	// Atributos de um comentário, incluindo ID e corpo
    	private Long id;
        private String body;
        
        // Construtor para criar um novo comentário com um ID e corpo fornecidos
        public Comment(Long id, String body) {
            this.id = id;
            this.body = body;
        }

        public Long getId() {
            return id;
        }

        public String getBody() {
            return body;
        }

        // Getters and setters for all fields
        // ...
    }

    // Classe interna que representa uma entrada de histórico
    public class HistoryEntry {
        // Atributos de uma entrada de histórico, incluindo ID, data e status
    	private Long id;
        private String date;
        private String status;

        // Construtor para criar uma nova entrada de histórico com um ID, data e status fornecidos
        public HistoryEntry(Long id, String date, String status) {
            this.id = id;
            this.date = date;
            this.status = status;
        }

        public Long getId() {
            return id;
        }

        public String getDate() {
            return date;
        }

        public String getStatus() {
            return status;
        }

        // Getters and setters for all fields
        // ...
    }
}
