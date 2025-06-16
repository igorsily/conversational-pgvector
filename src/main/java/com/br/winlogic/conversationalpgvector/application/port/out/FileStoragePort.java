package com.br.winlogic.conversationalpgvector.application.port.out;

import java.io.InputStream;
import java.util.List;

public interface FileStoragePort {

    void save(String path, InputStream fileStream);

    InputStream load(String path);

    void delete(String path);

//    List<InputStream> readAllFiles();

}
