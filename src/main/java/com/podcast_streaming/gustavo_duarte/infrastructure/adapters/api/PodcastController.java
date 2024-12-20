package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.podcast_streaming.gustavo_duarte.application.queries.podcasts.FindPodcastService;
import com.podcast_streaming.gustavo_duarte.application.queries.podcasts.ListPodcastsService;
import com.podcast_streaming.gustavo_duarte.application.services.podcasts.CreatePodcastService;
import com.podcast_streaming.gustavo_duarte.application.services.podcasts.DeletePodcastService;
import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;
import com.podcast_streaming.gustavo_duarte.model.presenter.PodcastPresenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/stream-channels/{streamChannelUuid}/podcasts")
public class PodcastController {
  private CreatePodcastService createPodcastService;
  private ListPodcastsService listPodcastsService;
  private FindPodcastService findPodcastService;
  private DeletePodcastService  deletePodcastService;

  @Autowired
  public PodcastController(
    CreatePodcastService createPodcastService,
    ListPodcastsService listPodcastsService,
    FindPodcastService findPodcastService,
    DeletePodcastService  deletePodcastService
  ) {
    this.createPodcastService = createPodcastService;
    this.listPodcastsService = listPodcastsService;
    this.findPodcastService = findPodcastService;
    this.deletePodcastService = deletePodcastService;
  }

  @PostMapping()
  @Operation(summary = "Cria um novo podcast")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Podcast criado")
  })
  public Podcast createPodcast(@RequestBody PodcastPresenter podcastPresenter, @PathVariable String streamChannelUuid) {
    podcastPresenter.setStreamChannelUuid(streamChannelUuid);
    return createPodcastService.create(podcastPresenter.toDomain(),null);
  }

  @PostMapping(path="/upload")
  @Operation(summary = "Faz upload de um novo podcast")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Podcast criado")
  })
  public Podcast uploadPodcast(
        @RequestPart("file") MultipartFile file,
        @RequestPart("title") String title,
        @RequestPart("description") String description,
        @RequestPart("releaseDate") String releaseDate,
        @PathVariable String streamChannelUuid
      ) {
    PodcastPresenter podcastPresenter = new PodcastPresenter();
    podcastPresenter.setTitle(title);
    podcastPresenter.setDescription(description);
    podcastPresenter.setReleaseDate(releaseDate);
    podcastPresenter.setStreamChannelUuid(streamChannelUuid);
    return createPodcastService.create(podcastPresenter.toDomain(), file);
  }

  @GetMapping()
  @Operation(summary = "Lista todos os podcasts")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de podcasts")
  })
  public List<Podcast> listPodcasts(
    @RequestParam(defaultValue = "0") String page,
    @PathVariable String streamChannelUuid
  ){
    return listPodcastsService.listPodcasts(page, streamChannelUuid);
  }

  @GetMapping("/{podcastsUuid}")
  @Operation(summary = "Busca um podcast")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Podcast encontrado")
  })
  public Podcast findPodcast(@PathVariable String streamChannelUuid, @PathVariable String podcastsUuid){
    return findPodcastService.findPodcast(streamChannelUuid, podcastsUuid);
  }

  @DeleteMapping("/{podcastsUuid}")
  @Operation(summary = "Deleta um podcast")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Podcast deletado")
  })
  public void deletePodcast(@PathVariable String streamChannelUuid, @PathVariable String podcastsUuid){
    deletePodcastService.delete(streamChannelUuid, podcastsUuid);
  }
}
