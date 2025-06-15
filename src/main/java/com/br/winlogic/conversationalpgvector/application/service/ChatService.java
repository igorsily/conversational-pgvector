package com.br.winlogic.conversationalpgvector.application.service;

import com.br.winlogic.conversationalpgvector.adapter.chat.OpenAiChatAdapter;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final OpenAiChatAdapter openAiChatAdapter;

    public ChatService(OpenAiChatAdapter openAiChatAdapter) {
        this.openAiChatAdapter = openAiChatAdapter;
    }

    public String chat(String message) {
        return openAiChatAdapter.chat(message);
    }

}
