package com.br.winlogic.conversationalpgvector.adapter.in.rest;

import com.br.winlogic.conversationalpgvector.adapter.in.rest.dto.UploadFileRequest;
import com.br.winlogic.conversationalpgvector.application.dto.UploadFileCommand;
import com.br.winlogic.conversationalpgvector.application.service.FileStorageService;
import com.br.winlogic.conversationalpgvector.application.dto.Metadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/file-upload")
public class FileUploadController {

    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @PostMapping
    public ResponseEntity<?> uploadFile(@ModelAttribute UploadFileRequest request) {
        try {
            UploadFileCommand command = new UploadFileCommand(
                    request.file().getOriginalFilename(),
                    request.file().getInputStream(),
                    request.file().getContentType(),
                    new Metadata(request.tag(), request.description())
            );

            this.fileStorageService.upload(command);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
