package com.br.winlogic.conversationalpgvector.adapter.out.persistence.mapper;

import com.br.winlogic.conversationalpgvector.adapter.out.persistence.entity.ChatCostEntity;
import com.br.winlogic.conversationalpgvector.domain.model.ChatCost;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface ChatCostMapper {

    ChatCostEntity toEntity(ChatCost chatCost);

    ChatCost toDomain(ChatCostEntity chatCostEntity);
}
