package com.podcast_streaming.gustavo_duarte.application.queries.stream_channels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

@Service
public class ListStreamChannelsService {
  private StreamChannelRepository streamChannelRepository;

  @Autowired
  public ListStreamChannelsService(StreamChannelRepository streamChannelRepository) {
    this.streamChannelRepository = streamChannelRepository;
  }

  public Iterable<StreamChannel> listStreamChannels() {
    return streamChannelRepository.findAll();
  }
}
