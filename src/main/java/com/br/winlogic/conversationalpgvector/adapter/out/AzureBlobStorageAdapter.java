package com.br.winlogic.conversationalpgvector.adapter.out;

import com.azure.storage.blob.*;
import com.br.winlogic.conversationalpgvector.application.port.out.FileStoragePort;

import java.io.InputStream;

public class AzureBlobStorageAdapter implements FileStoragePort {

//    private final String connectionString;
//
//    private final String containerName;

//    private final BlobServiceClient blobServiceClient;

    private final BlobContainerClient containerClient;

    public AzureBlobStorageAdapter(String connectionString,
                                   String containerName) {
//        this.connectionString = connectionString;
//
//        this.containerName = containerName;

//        this.blobServiceClient = new BlobServiceClientBuilder()
//                .connectionString(connectionString)
//                .buildClient();

        this.containerClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient()
                .getBlobContainerClient(containerName);

    }


    @Override
    public void save(String path, InputStream fileStream) {

        this.getBlobClient(path).upload(fileStream, true);
    }

    @Override
    public InputStream load(String path) {
        return this.getBlobClient(path).openInputStream();

    }

    @Override
    public void delete(String path) {

        this.getBlobClient(path).deleteIfExists();
    }

    private BlobClient getBlobClient(String path) {
        return containerClient.getBlobClient(path);
    }


//    private void createContainer(String containerName) {
//        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
//
//        if (!blobContainerClient.exists()) {
//            blobContainerClient.create();
//            System.out.println("Container '" + containerName + "' created successfully.");
//        } else {
//            System.out.println("Container '" + containerName + "' already exists.");
//        }
//    }

}
