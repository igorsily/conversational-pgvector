package com.br.winlogic.conversationalpgvector.adapter.out;

import com.br.winlogic.conversationalpgvector.application.port.out.FileStoragePort;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalFileStorageAdapter implements FileStoragePort {

    private final String basePath;

    public LocalFileStorageAdapter(String basePath) {

        assert basePath != null;

        this.basePath = basePath;
    }

    @Override
    public void save(String path, InputStream fileStream) {
        try {
            Path filePath = Paths.get(basePath, path);
            Files.createDirectories(filePath.getParent());
            Files.copy(fileStream, filePath);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo local", e);
        }
    }

    @Override
    public InputStream load(String path) {
        try {
            return Files.newInputStream(Paths.get(basePath, path));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo local", e);
        }
    }

    @Override
    public void delete(String path) {
        try {
            Files.deleteIfExists(Paths.get(basePath, path));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao deletar arquivo local", e);
        }
    }
}
