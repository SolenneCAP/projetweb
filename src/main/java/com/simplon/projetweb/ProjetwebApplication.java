package com.simplon.projetweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.simplon.projetweb")
public class ProjetwebApplication {

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjetwebApplication.class, args);
    }

}
