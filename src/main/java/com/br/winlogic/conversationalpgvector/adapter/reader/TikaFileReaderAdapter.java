package com.br.winlogic.conversationalpgvector.adapter.reader;

import com.br.winlogic.conversationalpgvector.application.dto.Metadata;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TikaFileReaderAdapter {

    private final PgVectorStore vectorStore;

    public TikaFileReaderAdapter(PgVectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void processFilesInDirectory(String directoryPath) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            paths.filter(Files::isRegularFile).forEach(this::addResource);
        }
    }

    public void addResource(Path path) {
        Resource resource = new FileSystemResource(path.toFile());
        TikaDocumentReader tikaDocumentReaderConfig = new TikaDocumentReader(resource);
        TokenTextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(tikaDocumentReaderConfig.get()));
    }

    public void addResource(Resource resource) {
        TikaDocumentReader tikaDocumentReaderConfig = new TikaDocumentReader(resource);
        TokenTextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(tikaDocumentReaderConfig.get()));
    }

    public void addResource(Resource resource, Metadata metadata) {

        TikaDocumentReader tikaDocumentReaderConfig = new TikaDocumentReader(resource);


        List<Document> documents = tikaDocumentReaderConfig.get();

        documents.forEach(document -> {
            document.getMetadata().put(metadata.key(), metadata.value());
            document.getMetadata().put("source", resource.getFilename());
        });

        TokenTextSplitter textSplitter = new TokenTextSplitter();

        vectorStore.accept(textSplitter.apply(documents));

    }
}
