package com.podcast_streaming.gustavo_duarte.application.queries.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PublisherRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;

@Service
public class FindPublisherService {
  private PublisherRepository publisherRepository;
  
  @Autowired
  public FindPublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  public Publisher findPublisher(String uuid) {
    return publisherRepository.findByUuid(uuid);
  }
}
