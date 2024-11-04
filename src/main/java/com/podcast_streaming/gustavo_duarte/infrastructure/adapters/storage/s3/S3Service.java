package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.storage.s3;

import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
public class S3Service {
  private S3Config s3Config;

  @Autowired
  public S3Service(S3Config s3Config) {
    this.s3Config = s3Config;
  }

  public String store(String key, MultipartFile data) {
    try {
      S3Client s3Client = s3Config.s3Client();
      InputStream inputStream = data.getInputStream();
      PutObjectRequest putObjectRequest = PutObjectRequest.builder()
              .bucket(s3Config.getBucketName())
              .key(key)
              .build();
      RequestBody requestBody = RequestBody.fromInputStream(inputStream, data.getSize());

      PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, requestBody);
      
      String url = putObjectResponse.eTag();
      
      GetUrlRequest getUrlRequest = GetUrlRequest.builder()
              .bucket(s3Config.getBucketName())
              .key(key)
              .build();

      return s3Client.utilities().getUrl(getUrlRequest).toExternalForm();
    } catch (Exception e) {
      throw new RuntimeException("Error to store file in S3", e);

    }
  }
}
