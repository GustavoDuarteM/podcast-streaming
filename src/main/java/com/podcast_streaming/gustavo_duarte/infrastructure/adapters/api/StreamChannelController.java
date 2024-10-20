package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.stream_channels.ListStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.application.services.stream_channels.CreateStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;
import com.podcast_streaming.gustavo_duarte.model.presenter.StreamChannelPresenter;

@RestController
@RequestMapping("/api/stream-channels")
public class StreamChannelController {
  private ListStreamChannelsService listStreamChannelsService;
  private CreateStreamChannelsService createStreamChannelsService;
  
  @Autowired
  public StreamChannelController(
    ListStreamChannelsService listStreamChannelsService,
    CreateStreamChannelsService createStreamChannelsService
  ) {
    this.listStreamChannelsService = listStreamChannelsService;
    this.createStreamChannelsService = createStreamChannelsService;
  }

  @GetMapping()
  public Iterable<StreamChannel> listStreamChannels() {
    return listStreamChannelsService.listStreamChannels();
  }

  @PostMapping()
  public StreamChannel createStreamChannel(@RequestBody StreamChannelPresenter streamChannelPresenter) {
    return createStreamChannelsService.create(streamChannelPresenter.toDomain());
  }
}
