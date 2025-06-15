package com.br.winlogic.conversationalpgvector.adapter.out.persistence;

import com.br.winlogic.conversationalpgvector.adapter.out.persistence.jpa.ChatCostJpaRepository;
import com.br.winlogic.conversationalpgvector.adapter.out.persistence.mapper.ChatCostMapper;
import com.br.winlogic.conversationalpgvector.application.port.out.ChatCostRepository;
import com.br.winlogic.conversationalpgvector.domain.model.ChatCost;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class ChatCostRepositoryAdapter implements ChatCostRepository {

    private final Double COST_PER_INPUT_TOKEN = 0.000005;
    private final Double COST_PER_OUTPUT_TOKEN = 0.000015;


    private final ChatCostJpaRepository jpaRepository;

    private final ChatCostMapper chatCostMapper;

    public ChatCostRepositoryAdapter(ChatCostJpaRepository jpaRepository, ChatCostMapper chatCostMapper) {
        this.jpaRepository = jpaRepository;
        this.chatCostMapper = chatCostMapper;
    }

    @Override
    @Async
    public CompletableFuture<Void> saveChatCost(ChatCost chatCost) {

        var entity = chatCostMapper.toEntity(chatCost);

        var costInput = chatCost.getPromptTokens() * COST_PER_INPUT_TOKEN;
        var costOutput = chatCost.getCompletionTokens() * COST_PER_INPUT_TOKEN;
        var totalCost = costInput + costOutput;

        entity.setCostInput(costInput);
        entity.setCostOutput(costOutput);
        entity.setCostTotal(totalCost);


        jpaRepository.save(entity);

        return CompletableFuture.completedFuture(null);

    }
}
