package com.br.winlogic.conversationalpgvector.adapter.in.rest;

import com.br.winlogic.conversationalpgvector.application.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String chat(String message) {
        return chatService.chat(message);
    }
}
