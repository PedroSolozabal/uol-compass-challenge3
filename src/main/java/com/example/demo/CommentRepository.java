package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Importa a classe List

// Interface que extende JpaRepository para interagir com a entidade Comment no banco de dados
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Métodos de consulta personalizados podem ser definidos aqui, se necessário

    // Exemplo de método de consulta personalizado para buscar comentários por autor
    List<Comment> findByAuthor(String author); // Método que retorna uma lista de Comment
}

