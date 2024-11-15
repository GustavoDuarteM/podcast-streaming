package com.podcast_streaming.gustavo_duarte.application.services.podcasts;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.MediaRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.PodcastRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db.StreamChannelRepository;
import com.podcast_streaming.gustavo_duarte.infrastructure.adapters.storage.s3.UploadFileStorageService;
import com.podcast_streaming.gustavo_duarte.model.domain.Media;
import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

import jakarta.transaction.Transactional;

@Service
public class CreatePodcastService {
  private StreamChannelRepository streamChannelRepository;
  private PodcastRepository podcastRepository;
  private MediaRepository mediaRepository;
  private UploadFileStorageService uploadFileStorageService; 

  @Autowired
  public CreatePodcastService(
    StreamChannelRepository streamChannelRepository,
    PodcastRepository podcastRepository,
    UploadFileStorageService uploadFileStorageService,
    MediaRepository mediaRepository
  ) {
    this.streamChannelRepository = streamChannelRepository;
    this.podcastRepository = podcastRepository;
    this.uploadFileStorageService = uploadFileStorageService;
    this.mediaRepository = mediaRepository;
  }

  @Transactional
  public Podcast create(Podcast podcast, MultipartFile contentFile){
    StreamChannel streamChannel = streamChannelRepository.findByUuid(podcast.getStreamChannel().getUuid());
    if (streamChannel == null) {
      throw new RuntimeException("Stream Channel not found");
    }
    
    Map<String, String> uploadResult = uploadFileStorageService.upload(contentFile);
    
    Media new_media = initializeMedia(contentFile, uploadResult);
    Podcast new_podcast = initializePodcast(podcast, streamChannel);
    
    podcastRepository.save(new_podcast);

    new_media.setContent(new_podcast);
    mediaRepository.save(new_media);
    
    if(new_podcast.getId() == null){
      throw new RuntimeException("Podcast not created");
    }
    else {
      return new_podcast;
    }
  }

  private Podcast initializePodcast(Podcast podcast, StreamChannel streamChannel) {
    Podcast new_podcast = new Podcast();
    new_podcast.setUuid(UUID.randomUUID().toString());
    new_podcast.setTitle(podcast.getTitle());
    new_podcast.setDescription(podcast.getDescription());
    new_podcast.setReleaseDate(podcast.getReleaseDate());
    new_podcast.setStreamChannel(streamChannel);
    return new_podcast;
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