package com.br.winlogic.conversationalpgvector.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class OpenAiConfig {

    private final String apiKey;


    public OpenAiConfig(
            @Value("${spring.ai.openai.api-key}") String apiKey
    ) {

        this.apiKey = apiKey;
    }

    @Bean
    public OpenAiApi openAiApi() {
        return OpenAiApi.builder()
                .apiKey(apiKey)
                .build();
    }

//    @Bean
//    public EmbeddingModel openAiEmbeddingModel(OpenAiApi openAiApi) {
//        return new OpenAiEmbeddingModel(openAiApi);
//    }

    @Bean
    public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .build();
    }

    @Bean
    public PgVectorStore pgVectorStore(JdbcTemplate jdbcTemplate, EmbeddingModel openAiEmbeddingModel) {
        return PgVectorStore.builder(jdbcTemplate, openAiEmbeddingModel)
                .build();
    }
}
