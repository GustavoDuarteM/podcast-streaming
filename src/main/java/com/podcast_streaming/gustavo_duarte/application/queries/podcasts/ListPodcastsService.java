package com.podcast_streaming.gustavo_duarte.application.queries.podcasts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  public List<Podcast> listPodcasts(String page, String streamChannelUuid) {
    StreamChannel streamChannel = streamChannelRepository.findByUuid(streamChannelUuid);
    page = page == null ? "0" : page;
    Pageable pageable = PageRequest.of(Integer.parseInt(page), 10);

    return podcastRepository.findBystreamChannelId(pageable, streamChannel.getId());
  }
} 
