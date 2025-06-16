package com.br.winlogic.conversationalpgvector.adapter.in.rest;

import com.br.winlogic.conversationalpgvector.adapter.in.rest.dto.ChatRequest;
import com.br.winlogic.conversationalpgvector.application.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return chatService.chat(request.message());
    }
}
