package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// Indica que essa classe é a classe de aplicação Spring Boot
@SpringBootApplication
// Habilita o suporte para execução assíncrona (métodos assíncronos)
@EnableAsync
// Habilita o agendamento de tarefas agendadas (scheduling)
@EnableScheduling
public class Application {

    // Método principal que inicia a aplicação Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
