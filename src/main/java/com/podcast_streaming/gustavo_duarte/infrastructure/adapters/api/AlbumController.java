package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.albums.ListAlbumsService;
import com.podcast_streaming.gustavo_duarte.model.domain.Album;

@RestController
@RequestMapping("/api/stream-channels/{streamChannelUuid}/albums")
public class AlbumController {

  private ListAlbumsService listAlbumsService;

  @Autowired
  public AlbumController(ListAlbumsService listAlbumsService) {
    this.listAlbumsService = listAlbumsService;
  }

  @GetMapping()
  public Iterable<Album> listAlbums(@PathVariable String streamChannelUuid){
    return listAlbumsService.listAlbums(streamChannelUuid);
  }
  
}
