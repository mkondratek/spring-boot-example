package com.kondratek.kalamba;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KalambaApplication {

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(KalambaApplication.class, args);
    }

}
