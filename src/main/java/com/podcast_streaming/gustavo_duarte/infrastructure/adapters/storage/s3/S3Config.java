package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.storage.s3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {
    @Getter
    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Getter
    @Value("${aws.s3.access-key}")
    private String accessKey;
    
    @Getter
    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Getter
    @Value("${aws.s3.region}")
    private String region;

    @Getter
    @Value("${aws.s3.endpoint}")
    private String endpoint;


    public S3Client s3Client() {
        return S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
            .endpointOverride(URI.create(endpoint))
            .build();
    }
}