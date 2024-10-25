package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.podcasts.ListPodcastsService;
import com.podcast_streaming.gustavo_duarte.application.services.podcasts.CreatePodcastService;
import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;
import com.podcast_streaming.gustavo_duarte.model.presenter.PodcastPresenter;

@RestController
@RequestMapping("/api/stream-channels/{streamChannelUuid}/podcasts")
public class PodcastController {
  private CreatePodcastService createPodcastService;
  private ListPodcastsService listPodcastsService;

  @Autowired
  public PodcastController(
  CreatePodcastService createPodcastService,
  ListPodcastsService listPodcastsService) {
    this.createPodcastService = createPodcastService;
    this.listPodcastsService = listPodcastsService;
  }

  @PostMapping()
  public Podcast createPodcast(@RequestBody PodcastPresenter podcastPresenter, @PathVariable String streamChannelUuid) {
    podcastPresenter.setStreamChannelUuid(streamChannelUuid);
    return createPodcastService.create(podcastPresenter.toDomain());
  }

  @GetMapping()
  public Iterable<Podcast> listPodcasts(@PathVariable String streamChannelUuid){
    return listPodcastsService.listPodcasts(streamChannelUuid);
  }
}
