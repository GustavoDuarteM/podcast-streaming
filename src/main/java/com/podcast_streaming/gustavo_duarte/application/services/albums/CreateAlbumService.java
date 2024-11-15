package com.podcast_streaming.gustavo_duarte.application.services.albums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.AlbumRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.MediaRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.storage.s3.UploadFileStorageService;
import com.podcast_streaming.gustavo_duarte.model.domain.Album;
import com.podcast_streaming.gustavo_duarte.model.domain.Media;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

import jakarta.transaction.Transactional;

@Service
public class CreateAlbumService {
  private StreamChannelRepository streamChannelRepository;
  private MediaRepository mediaRepository;
  private UploadFileStorageService uploadFileStorageService;
  private AlbumRepository albumRepository;

  @Autowired
  public CreateAlbumService(
      StreamChannelRepository streamChannelRepository,
      AlbumRepository albumRepository,
      UploadFileStorageService uploadFileStorageService,
      MediaRepository mediaRepository) {
    this.streamChannelRepository = streamChannelRepository;
    this.albumRepository = albumRepository;
    this.uploadFileStorageService = uploadFileStorageService;
    this.mediaRepository = mediaRepository;
  }

  @Transactional
  public Album create(Album album, MultipartFile[] contentFiles) {
    StreamChannel streamChannel = streamChannelRepository.findByUuid(album.getStreamChannel().getUuid());
    if (streamChannel == null) {
      throw new RuntimeException("Stream Channel not found");
    }

    List<Media> new_medias = initializeMedias(contentFiles);
    Album new_album = initializeAlbum(album, streamChannel);

    albumRepository.save(new_album);
    for (Media media : new_medias) {
      media.setContent(new_album);
      mediaRepository.save(media);
    }

    if (new_album.getId() == null) {
      throw new RuntimeException("Album not created");
    } else {
      return new_album;
    }
  }
  
  private Album initializeAlbum(Album album, StreamChannel streamChannel){
    Album new_album = new Album();
    new_album.setUuid(UUID.randomUUID().toString());
    new_album.setTitle(album.getTitle());
    new_album.setDescription(album.getDescription());
    new_album.setReleaseDate(album.getReleaseDate());
    new_album.setArtist(album.getArtist());
    new_album.setGenre(album.getGenre());
    new_album.setTracks(album.getTracks());
    new_album.setYear(album.getYear());
    new_album.setStreamChannel(streamChannel);
    return new_album;
  }

  private List<Media> initializeMedias(MultipartFile[] contentFiles){
    List<Media> new_medias = new ArrayList<>();

    for (MultipartFile multipartFile : contentFiles) {
      Map<String, String> uploadResult = uploadFileStorageService.upload(multipartFile);
      Media new_media = initializeMedia(multipartFile, uploadResult);
      new_medias.add(new_media);
    }

    return new_medias;
  } 

  private Media initializeMedia(MultipartFile contentFile, Map<String, String> uploadResult) {
    Media media = new Media();
    media.setUuid(UUID.randomUUID().toString());
    media.setFileURL(uploadResult.get("urlFileUploaded"));
    media.setFileKey(uploadResult.get("key"));
    media.setFileName(contentFile.getOriginalFilename());
    media.setFileExtension(contentFile.getContentType());
    media.setDurationTimeSeconds(0);

    return media;
  }
}
