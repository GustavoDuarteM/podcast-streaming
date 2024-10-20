package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.publishers.FindPublisherService;
import com.podcast_streaming.gustavo_duarte.application.queries.publishers.ListPublishersService;
import com.podcast_streaming.gustavo_duarte.application.services.publishers.CreatePublisherService;
import com.podcast_streaming.gustavo_duarte.application.services.publishers.DeletePublisherService;
import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
  private ListPublishersService listPublishersService;
  private CreatePublisherService createPublisherService;
  private FindPublisherService findPublisherService;
  private DeletePublisherService deletePublisherService;
  @Autowired
  public PublisherController(
    ListPublishersService listPublishersService,
    CreatePublisherService createPublisherService,
    FindPublisherService findPublisherService,
    DeletePublisherService deletePublisherService
  ) {
    this.listPublishersService = listPublishersService;
    this.createPublisherService = createPublisherService;
    this.findPublisherService = findPublisherService;
    this.deletePublisherService = deletePublisherService;
  }

  @GetMapping()
  public Iterable<Publisher> listPublishers() {
    return listPublishersService.listPublishers();
  }
  
  @PostMapping()
  public Publisher createPublisher() {
    return createPublisherService.create(new Publisher());
  }

  @GetMapping("/{uuid}")
  public Publisher findPublisher(@PathVariable String uuid) {
    return findPublisherService.findPublisher(uuid);
  }

  @DeleteMapping("/{uuid}")
  public void deletePublisher(@PathVariable String uuid) {
    deletePublisherService.delete(uuid);
  }
}
