package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.publishers.ListPublishersService;
import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
  private ListPublishersService listPublishersService;

  @Autowired
  public PublisherController(ListPublishersService listPublishersService) {
    this.listPublishersService = listPublishersService;
  }

  @GetMapping()
  public Iterable<Publisher> listPublishers() {
    return listPublishersService.listPublishers();
  } 
}
