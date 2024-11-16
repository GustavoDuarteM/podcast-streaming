package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.podcast_streaming.gustavo_duarte.application.queries.albums.ListAlbumsService;
import com.podcast_streaming.gustavo_duarte.application.services.albums.CreateAlbumService;
import com.podcast_streaming.gustavo_duarte.model.domain.Album;
import com.podcast_streaming.gustavo_duarte.model.presenter.AlbumPresenter;

@RestController
@RequestMapping("/api/stream-channels/{streamChannelUuid}/albums")
public class AlbumController {

  private ListAlbumsService listAlbumsService;
  private CreateAlbumService createAlbumService;
  
  @Autowired
  public AlbumController(
  ListAlbumsService listAlbumsService,
  CreateAlbumService createAlbumService
  ) {
    this.listAlbumsService = listAlbumsService;
    this.createAlbumService = createAlbumService;
  }

  @GetMapping()
  public List<Album> listAlbums(
    @RequestParam(defaultValue = "0") String page,
    @PathVariable String streamChannelUuid
  ){
    return listAlbumsService.listAlbums(page, streamChannelUuid);
  }

  @PostMapping()
  public Album createAlbum(
  @RequestPart("files") MultipartFile[] files,
  @RequestPart("album") AlbumPresenter albumPresenter,
  @PathVariable String streamChannelUuid) {
    albumPresenter.setStreamChannelUuid(streamChannelUuid);
    return createAlbumService.create(albumPresenter.toDomain(), files);
  }
  
}
