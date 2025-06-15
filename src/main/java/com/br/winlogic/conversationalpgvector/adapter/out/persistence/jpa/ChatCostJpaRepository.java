package com.br.winlogic.conversationalpgvector.adapter.out.persistence.jpa;

import com.br.winlogic.conversationalpgvector.adapter.out.persistence.entity.ChatCostEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChatCostJpaRepository extends CrudRepository<ChatCostEntity, Long> {
}
