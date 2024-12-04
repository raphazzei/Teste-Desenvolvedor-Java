package com.teste.pratico.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite todas as origens (domínios) e todos os métodos HTTP
        registry.addMapping("/api/**") // Caminho da sua API
                .allowedOrigins("http://localhost:4200") // Permite o front-end rodando no localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite os métodos que você vai usar
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}
