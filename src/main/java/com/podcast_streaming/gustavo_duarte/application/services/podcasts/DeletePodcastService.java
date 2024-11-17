package com.podcast_streaming.gustavo_duarte.application.services.podcasts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PodcastRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

import jakarta.transaction.Transactional;

@Service
public class DeletePodcastService {
  private PodcastRepository podcastRepository;
  private StreamChannelRepository streamChannelRepository;
  
  @Autowired
  public DeletePodcastService(
    PodcastRepository podcastRepository, 
    StreamChannelRepository streamChannelRepository
  ) {
    this.podcastRepository = podcastRepository;
    this.streamChannelRepository = streamChannelRepository;
  }

  @Transactional
  public void delete(String streamChannelUuid, String uuid){
    StreamChannel streamChannel = streamChannelRepository.findByUuid(streamChannelUuid);
    podcastRepository.deleteBystreamChannelIdAndUuid(streamChannel.getId(), uuid);
  }
}
