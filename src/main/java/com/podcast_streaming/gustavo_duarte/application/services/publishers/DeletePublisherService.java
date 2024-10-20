package com.podcast_streaming.gustavo_duarte.application.services.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PublisherRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;

import jakarta.transaction.Transactional;

@Service
public class DeletePublisherService {
  private final PublisherRepository publisherRepository;

  @Autowired
  public DeletePublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }
  
  @Transactional
  public void delete(String uuid) {
    Publisher publisher = publisherRepository.findByUuid(uuid);
    if (publisher == null) {
      throw new RuntimeException("Publisher not found");
    }
    publisherRepository.delete(publisher);
  }
}