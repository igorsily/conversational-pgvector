package com.br.winlogic.conversationalpgvector.application.port.out;

import java.io.InputStream;

public interface FileStoragePort {

    void save(String path, InputStream fileStream);

    InputStream load(String path);

    void delete(String path);

}
