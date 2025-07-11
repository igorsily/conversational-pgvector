package com.br.winlogic.conversationalpgvector.adapter.chat;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.br.winlogic.conversationalpgvector.application.port.out.ChatCostRepository;
import com.br.winlogic.conversationalpgvector.domain.model.ChatCost;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class OpenAiChatAdapter {


    private final PgVectorStore pgVectorStore;
    private final OpenAiChatModel openAiChat;

    private final Resource template;

    private final ChatCostRepository chatCostRepository;

    public OpenAiChatAdapter(
            PgVectorStore pgVectorStore,
            OpenAiChatModel openAiChat,
            @Value("classpath:/prompts/assist.st") Resource template, ChatCostRepository chatCostRepository
    ) {
        this.pgVectorStore = pgVectorStore;
        this.openAiChat = openAiChat;
        this.template = template;
        this.chatCostRepository = chatCostRepository;
    }

    public String chat(String message) {
        List<Document> documents = this.pgVectorStore.similaritySearch(message);
        assert documents != null;
        String collect =
                documents.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));
        Message createdMessage = new SystemPromptTemplate(template).createMessage(Map.of("documents", collect, "query", message));
        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = new Prompt(List.of(createdMessage, userMessage));
        ChatResponse chatResponse = openAiChat.call(prompt);

        var usage = chatResponse.getMetadata().getUsage();

        this.chatCostRepository.saveChatCost(ChatCost.builder()
                .promptTokens(usage.getPromptTokens())
                .completionTokens(usage.getCompletionTokens())
                .totalTokens(usage.getTotalTokens())
                .build()
        );

        return chatResponse.getResults().stream().map(generation -> generation.getOutput().getText())
                .collect(Collectors.joining("/n"));
    }

}
