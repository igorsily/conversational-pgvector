package com.br.winlogic.conversationalpgvector.adapter.reader;

import com.br.winlogic.conversationalpgvector.application.dto.Metadata;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PdfFileReaderAdapter {

    private final PgVectorStore vectorStore;

    public PdfFileReaderAdapter(PgVectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void addResource(Resource pdfResource) {
        PdfDocumentReaderConfig pdfDocumentReaderConfig = PdfDocumentReaderConfig.builder()
                .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder().build()).build();
        PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(pdfResource, pdfDocumentReaderConfig);
        TokenTextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(pagePdfDocumentReader.get()));

    }

    public void addResource(Resource pdfResource, Metadata metadata) {
        PdfDocumentReaderConfig pdfDocumentReaderConfig = PdfDocumentReaderConfig.builder()
                .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder().build()).build();
        PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(pdfResource, pdfDocumentReaderConfig);

        pagePdfDocumentReader.get().forEach(document -> {
            document.getMetadata().put(metadata.key(), metadata.value());
        });


        TokenTextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(pagePdfDocumentReader.get()));

    }
}
