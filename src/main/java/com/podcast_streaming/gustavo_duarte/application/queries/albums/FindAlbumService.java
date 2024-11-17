package com.podcast_streaming.gustavo_duarte.application.queries.albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.AlbumRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Album;

@Service
public class FindAlbumService {
  private AlbumRepository albumRepository;

  @Autowired
  public FindAlbumService(AlbumRepository albumRepository){
    this.albumRepository = albumRepository;
  }

  public Album findAlbum(String albumUuid){
    return albumRepository.findByUuid(albumUuid);
  }
}
