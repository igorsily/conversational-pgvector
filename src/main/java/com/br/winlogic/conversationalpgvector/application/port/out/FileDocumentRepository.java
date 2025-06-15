package com.br.winlogic.conversationalpgvector.application.port.out;

import com.br.winlogic.conversationalpgvector.domain.model.FileDocument;

public interface FileDocumentRepository {
    void save(FileDocument file);

    void deleteByName(String filename);

    FileDocument findByName(String filename);
}
