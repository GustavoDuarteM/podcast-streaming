package com.podcast_streaming.gustavo_duarte.application.services.albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.AlbumRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.MediaRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeleteAlbumService {
  private AlbumRepository albumRepository;
  private StreamChannelRepository streamChannelRepository;
  @Autowired
  public DeleteAlbumService(
    AlbumRepository albumRepository,
    StreamChannelRepository streamChannelRepository,
    MediaRepository mediaRepository
  ) {
    this.albumRepository = albumRepository;
    this.streamChannelRepository = streamChannelRepository;
  }

  public void delete(String streamChannelUuid, String uuid){
    StreamChannel streamChannel = streamChannelRepository.findByUuid(streamChannelUuid);
    
    albumRepository.deleteBystreamChannelIdAndUuid(streamChannel.getId(), uuid);
    
  }
}
