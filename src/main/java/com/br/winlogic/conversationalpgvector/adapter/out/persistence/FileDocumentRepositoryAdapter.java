package com.br.winlogic.conversationalpgvector.adapter.out.persistence;

import com.br.winlogic.conversationalpgvector.application.port.out.FileDocumentRepository;
import com.br.winlogic.conversationalpgvector.domain.model.FileDocument;
import org.springframework.stereotype.Repository;

@Repository
public abstract class FileDocumentRepositoryAdapter implements FileDocumentRepository {

//    private final FileDocumentJpaRepository jpaRepository;
//
//    public FileDocumentRepositoryAdapter(FileDocumentJpaRepository jpaRepository) {
//        this.jpaRepository = jpaRepository;
//    }
//
//    @Override
//    public void save(FileDocument file) {
//        jpaRepository.save(FileDocumentEntity.fromDomain(file));
//    }

}
