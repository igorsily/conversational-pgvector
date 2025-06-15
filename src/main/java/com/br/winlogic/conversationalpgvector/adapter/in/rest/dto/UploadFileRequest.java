package com.br.winlogic.conversationalpgvector.adapter.in.rest.dto;

import org.springframework.web.multipart.MultipartFile;

public record UploadFileRequest(
        MultipartFile file,
        String description,
        String tag
) {
}
