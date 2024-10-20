package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.publishers.ListPublishersService;
import com.podcast_streaming.gustavo_duarte.application.services.publishers.CreatePublisherService;
import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
  private ListPublishersService listPublishersService;
  private CreatePublisherService createPublisherService;
  
  @Autowired
  public PublisherController(
    ListPublishersService listPublishersService,
    CreatePublisherService createPublisherService
  ) {
    this.listPublishersService = listPublishersService;
    this.createPublisherService = createPublisherService;
  }

  @GetMapping()
  public Iterable<Publisher> listPublishers() {
    return listPublishersService.listPublishers();
  }
  
  @PostMapping()
  public Publisher createPublisher() {
    return createPublisherService.create(new Publisher());
  }
}
