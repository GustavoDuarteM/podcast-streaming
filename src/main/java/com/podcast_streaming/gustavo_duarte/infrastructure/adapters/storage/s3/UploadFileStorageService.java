package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.storage.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadFileStorageService {
  private S3Service s3Service;

  @Autowired
  public UploadFileStorageService(S3Service s3Service) {
    this.s3Service = s3Service;
  }

  public Map<String, String> upload(MultipartFile data) {
    try {
      return uploadFile(data);
    } catch (Exception e) {
      throw new RuntimeException("Error uploading file");
    }

  }

  private Map<String, String> uploadFile(MultipartFile data) {
    String key = UUID.randomUUID().toString();
    String urlFileUploaded = s3Service.store(key, data);
    Map<String, String> result = new HashMap<>();
    result.put("urlFileUploaded", urlFileUploaded);
    result.put("key", key);
    return result;
  }
}
