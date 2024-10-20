package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.stream_channels.ListStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

@RestController
@RequestMapping("/api/stream-channels")
public class StreamChannelController {
  private ListStreamChannelsService listStreamChannelsService;
  
  @Autowired
  public StreamChannelController(
    ListStreamChannelsService listStreamChannelsService
  ) {
    this.listStreamChannelsService = listStreamChannelsService;
  }

  @GetMapping()
  public Iterable<StreamChannel> listStreamChannels() {
    return listStreamChannelsService.listStreamChannels();
  }
}
