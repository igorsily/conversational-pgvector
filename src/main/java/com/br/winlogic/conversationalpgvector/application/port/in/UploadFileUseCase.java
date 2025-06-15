package com.br.winlogic.conversationalpgvector.application.port.in;

import com.br.winlogic.conversationalpgvector.application.dto.UploadFileCommand;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileUseCase {
    void upload(UploadFileCommand uploadFileCommand);
}
