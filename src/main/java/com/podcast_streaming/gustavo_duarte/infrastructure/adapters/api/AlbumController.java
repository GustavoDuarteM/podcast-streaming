package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public Iterable<Album> listAlbums(@PathVariable String streamChannelUuid){
    return listAlbumsService.listAlbums(streamChannelUuid);
  }

  @PostMapping()
  public Album createAlbum(
  @RequestPart("files") MultipartFile[] files,
  @RequestPart("title") String title,
  @RequestPart("description") String description,
  @RequestPart("releaseDate") String releaseDate,
  @RequestPart("artist") String artist,
  @RequestPart("genre") String genre,
  @RequestPart("tracks") String tracks,
  @RequestPart("year") String year,
  @PathVariable String streamChannelUuid) {
    AlbumPresenter albumPresenter = new AlbumPresenter();
    albumPresenter.setTitle(title);
    albumPresenter.setDescription(description);
    albumPresenter.setReleaseDate(releaseDate);
    albumPresenter.setArtist(artist);
    albumPresenter.setGenre(genre);
    albumPresenter.setTracks(Integer.parseInt(tracks));
    albumPresenter.setYear(Integer.parseInt(year));
    albumPresenter.setStreamChannelUuid(streamChannelUuid);
    return createAlbumService.create(albumPresenter.toDomain(), files);
  }
  
}
