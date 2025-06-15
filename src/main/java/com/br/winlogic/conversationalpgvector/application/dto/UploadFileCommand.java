package com.br.winlogic.conversationalpgvector.application.dto;

import java.io.InputStream;

public record UploadFileCommand(String filename, InputStream inputStream, String contentType, Metadata metadata) {}
