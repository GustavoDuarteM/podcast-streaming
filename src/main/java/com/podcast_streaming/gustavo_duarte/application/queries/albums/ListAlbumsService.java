package com.podcast_streaming.gustavo_duarte.application.queries.albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.AlbumRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Album;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

@Service
public class ListAlbumsService {
  private AlbumRepository albumRepository;
  private StreamChannelRepository streamChannelRepository;

  @Autowired
  public ListAlbumsService(
  AlbumRepository albumRepository,
  StreamChannelRepository streamChannelRepository
  ) {
    this.albumRepository = albumRepository;
    this.streamChannelRepository = streamChannelRepository;
  }

  public Iterable<Album> listAlbums(String streamChannelUuid) {
    StreamChannel streamChannel = streamChannelRepository.findByUuid(streamChannelUuid); 

    return albumRepository.findBystreamChannelId(streamChannel.getId());
  }
}
