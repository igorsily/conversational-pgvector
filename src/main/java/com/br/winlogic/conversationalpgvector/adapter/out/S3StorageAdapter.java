package com.br.winlogic.conversationalpgvector.adapter.out;

import com.br.winlogic.conversationalpgvector.application.port.out.FileStoragePort;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.InputStream;

import static software.amazon.awssdk.core.sync.RequestBody.fromInputStream;

public class S3StorageAdapter implements FileStoragePort {

    private final S3Client s3Client;

    private final String bucketName;

    public S3StorageAdapter(
            String accessKey,
            String secretKey,
            String bucketName
    ) {

        this.bucketName = bucketName;

        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        this.s3Client = S3Client
                .builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    @Override
    public void save(String path, InputStream fileStream) {

        try {
            s3Client.putObject(
                    builder -> builder
                            .bucket(this.bucketName)
                            .key(path)
                            .build(),
                    fromInputStream(fileStream, fileStream.available())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public InputStream load(String path) {
        return s3Client.getObject(
                builder -> builder
                        .bucket(this.bucketName)
                        .key(path)
                        .build()
        );

    }

    @Override
    public void delete(String path) {
        s3Client.deleteObject(request ->
                request
                        .bucket(bucketName)
                        .key(path));
    }
}
