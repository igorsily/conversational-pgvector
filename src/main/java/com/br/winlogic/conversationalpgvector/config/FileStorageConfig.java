package com.br.winlogic.conversationalpgvector.config;

import com.br.winlogic.conversationalpgvector.adapter.out.AzureBlobStorageAdapter;
import com.br.winlogic.conversationalpgvector.adapter.out.LocalFileStorageAdapter;
import com.br.winlogic.conversationalpgvector.application.port.out.FileStoragePort;
import com.br.winlogic.conversationalpgvector.config.properties.FileStorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

    private final Logger logger = LoggerFactory.getLogger(FileStorageConfig.class);


    private final FileStorageProperties properties;

    public FileStorageConfig(FileStorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty(name = "file.storage.type", havingValue = "azure")
    public FileStoragePort azureFileStorage() {

        logger.info("AZURE FILE STORAGE ADAPTER ENABLED");

        return new AzureBlobStorageAdapter(
                properties.getAzure().getConnectionString(),
                properties.getAzure().getContainerName()
        );

    }

    @Bean
    @ConditionalOnProperty(name = "file.storage.type", havingValue = "local", matchIfMissing = true)
    public FileStoragePort localFileStorage() {

        logger.info("LOCAL FILE STORAGE ADAPTER ENABLED");

        return new LocalFileStorageAdapter(
                properties.getLocal().getBasePath()
        );
    }

}
