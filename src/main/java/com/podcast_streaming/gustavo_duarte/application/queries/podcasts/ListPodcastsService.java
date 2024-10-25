package com.podcast_streaming.gustavo_duarte.application.queries.podcasts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PodcastRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

@Service
public class ListPodcastsService {
  private PodcastRepository podcastRepository;
  private StreamChannelRepository streamChannelRepository;

  @Autowired
  public ListPodcastsService(
    PodcastRepository podcastRepository,
    StreamChannelRepository streamChannelRepository
  ) {
    this.podcastRepository = podcastRepository;
    this.streamChannelRepository = streamChannelRepository;
  }

  public Iterable<Podcast> listPodcasts(String streamChannelUuid) {
    StreamChannel streamChannel = streamChannelRepository.findByUuid(streamChannelUuid);
    
    return podcastRepository.findBystreamChannelId(streamChannel.getId());
  }
} 
