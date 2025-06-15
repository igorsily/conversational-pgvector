package com.br.winlogic.conversationalpgvector;

import com.br.winlogic.conversationalpgvector.config.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class ConversationalPgvectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConversationalPgvectorApplication.class, args);
    }

}
