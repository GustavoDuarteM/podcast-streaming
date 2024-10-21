package com.podcast_streaming.gustavo_duarte.application.services.stream_channels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

@Service
public class DeleteStreamChannelsService {
   private StreamChannelRepository streamChannelRepository;
  
  @Autowired
  public DeleteStreamChannelsService( StreamChannelRepository streamChannelRepository) {
    this.streamChannelRepository = streamChannelRepository;
  }

  public void delete(StreamChannel streamChannel) {
    StreamChannel streamChannelToDelete = streamChannelRepository.findByUuid(streamChannel.getUuid());
    streamChannelRepository.delete(streamChannelToDelete);
  }
}