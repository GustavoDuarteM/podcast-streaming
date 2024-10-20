package com.podcast_streaming.gustavo_duarte.application.services.stream_channels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PublisherRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

import jakarta.transaction.Transactional;

@Service
public class CreateStreamChannelsService {
  private StreamChannelRepository streamChannelRepository;
  private PublisherRepository publisherRepository;
  
  @Autowired
  public CreateStreamChannelsService(
  StreamChannelRepository streamChannelRepository,
  PublisherRepository publisherRepository
  ) {
    this.streamChannelRepository = streamChannelRepository;
    this.publisherRepository = publisherRepository;
  }
  
  @Transactional
  public StreamChannel create(StreamChannel streamChannel) {
    Publisher publisher = publisherRepository.findByUuid(streamChannel.getPublisher().getUuid());
    streamChannel.setUuid(java.util.UUID.randomUUID().toString());
    streamChannel.setPublisher(publisher);
    streamChannelRepository.save(streamChannel);
    if (streamChannel.getId() == null) {
      throw new RuntimeException("StreamChannel not created");
    }
    else {
      return streamChannel;
    }
  }
}