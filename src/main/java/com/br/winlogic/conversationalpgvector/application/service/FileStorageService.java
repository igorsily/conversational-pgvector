package com.br.winlogic.conversationalpgvector.application.service;

import com.br.winlogic.conversationalpgvector.adapter.reader.PdfFileReaderAdapter;
import com.br.winlogic.conversationalpgvector.adapter.reader.TikaFileReaderAdapter;
import com.br.winlogic.conversationalpgvector.application.dto.UploadFileCommand;
import com.br.winlogic.conversationalpgvector.application.port.in.UploadFileUseCase;
import com.br.winlogic.conversationalpgvector.application.port.out.FileStoragePort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.toByteArray;

@Service
public class FileStorageService implements UploadFileUseCase {

    private final FileStoragePort fileStoragePort;
    private final PdfFileReaderAdapter pdfReader;
    private final TikaFileReaderAdapter tikaReader;

    public FileStorageService(FileStoragePort fileStoragePort,
                              PdfFileReaderAdapter pdfReader,
                              TikaFileReaderAdapter tikaReader) {
        this.fileStoragePort = fileStoragePort;
        this.pdfReader = pdfReader;
        this.tikaReader = tikaReader;
    }

    @Override
    public void upload(UploadFileCommand uploadFileCommand) {

        if (uploadFileCommand.filename() == null) throw new IllegalArgumentException("Nome de arquivo inválido");

        try (InputStream inputStream = uploadFileCommand.inputStream()) {

            byte[] fileBytes = toByteArray(inputStream);

            // 1. Salva no destino (Azure ou Local)
            try (InputStream saveStream = new ByteArrayInputStream(fileBytes)) {
                fileStoragePort.save(uploadFileCommand.filename(), saveStream);
            }

            Resource resource = new ByteArrayResource(fileBytes);
            if (uploadFileCommand.filename().toLowerCase().endsWith(".pdf")) {
                pdfReader.addResource(resource, uploadFileCommand.metadata());
            } else {
                tikaReader.addResource(resource, uploadFileCommand.metadata());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
