package com.podcast_streaming.gustavo_duarte.application.services.stream_channels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

import jakarta.transaction.Transactional;

@Service
public class UpdateStreamChannelsService {
  private StreamChannelRepository streamChannelRepository;
  
  @Autowired
  public UpdateStreamChannelsService(
  StreamChannelRepository streamChannelRepository
  ) {
    this.streamChannelRepository = streamChannelRepository;
  }
  @Transactional
  public StreamChannel update(StreamChannel streamChannel) {
    StreamChannel streamChannelToUpdate = streamChannelRepository.findByUuid(streamChannel.getUuid());
    streamChannelToUpdate.setName(streamChannel.getName());
    streamChannelToUpdate.setDescription(streamChannel.getDescription());
    streamChannelRepository.save(streamChannelToUpdate);
    return streamChannelToUpdate;
  }
}