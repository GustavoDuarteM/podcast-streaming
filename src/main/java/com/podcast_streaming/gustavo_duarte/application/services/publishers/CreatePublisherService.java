package com.podcast_streaming.gustavo_duarte.application.services.publishers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;

import jakarta.transaction.Transactional;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PublisherRepository;

@Service
public class CreatePublisherService {
    private final PublisherRepository publisherRepository;
    
    @Autowired
    public CreatePublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public Publisher create(Publisher publisher) {
        publisher.setUuid(java.util.UUID.randomUUID().toString());
        publisherRepository.save(publisher);
        if (publisher.getId() == null) {
            throw new RuntimeException("Publisher not created");
        }
        else {
            return publisher;
        }
    }
}
