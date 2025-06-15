package com.br.winlogic.conversationalpgvector.application.port.out;

import com.br.winlogic.conversationalpgvector.domain.model.ChatCost;

import java.util.concurrent.CompletableFuture;

public interface ChatCostRepository {

    CompletableFuture<Void> saveChatCost(ChatCost chatCost);

}
