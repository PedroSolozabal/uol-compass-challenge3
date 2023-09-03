package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; // Importar a classe List

public interface PostHistoryRepository extends JpaRepository<PostHistory, Long> {
    // Métodos de consulta personalizados podem ser definidos aqui, se necessário

    // Exemplo de método de consulta personalizado para buscar post histories por título
    List<PostHistory> findByTitle(String title);
}
