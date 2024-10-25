package com.podcast_streaming.gustavo_duarte.application.services.podcasts;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PodcastRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

@Service
public class CreatePodcastService {
  private StreamChannelRepository streamChannelRepository;
  private PodcastRepository podcastRepository;
  
  @Autowired
  public CreatePodcastService(
    StreamChannelRepository streamChannelRepository,
    PodcastRepository podcastRepository
  ) {
    this.streamChannelRepository = streamChannelRepository;
    this.podcastRepository = podcastRepository;
  }

  public Podcast create(Podcast podcast){
    StreamChannel streamChannel = streamChannelRepository.findByUuid(podcast.getStreamChannel().getUuid());
    if (streamChannel == null) {
      throw new RuntimeException("Stream Channel not found");
    }
    
    Podcast new_podcast = new Podcast();
    new_podcast.setUuid(UUID.randomUUID().toString());
    new_podcast.setTitle(podcast.getTitle());
    new_podcast.setDescription(podcast.getDescription());
    new_podcast.setReleaseDate(podcast.getReleaseDate());
    new_podcast.setStreamChannel(streamChannel);

    podcastRepository.save(new_podcast);
    if(new_podcast.getId() == null){
      throw new RuntimeException("Podcast not created");
    }
    else {
      return new_podcast;
    }
  }
} 