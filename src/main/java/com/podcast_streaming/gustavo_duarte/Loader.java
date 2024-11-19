package com.podcast_streaming.gustavo_duarte;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import com.podcast_streaming.gustavo_duarte.application.services.albums.CreateAlbumService;
import com.podcast_streaming.gustavo_duarte.application.services.podcasts.CreatePodcastService;
import com.podcast_streaming.gustavo_duarte.application.services.publishers.CreatePublisherService;
import com.podcast_streaming.gustavo_duarte.application.services.stream_channels.CreateStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;
import com.podcast_streaming.gustavo_duarte.model.presenter.AlbumPresenter;
import com.podcast_streaming.gustavo_duarte.model.presenter.PodcastPresenter;
import com.podcast_streaming.gustavo_duarte.model.domain.Album;
import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;
import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;

@Component
public class Loader implements ApplicationRunner {
  private CreateStreamChannelsService createStreamChannelsService;
  private CreatePublisherService createPublisherService;
  private CreatePodcastService createPodcastService;
  private CreateAlbumService createAlbumService;
  
  @Autowired
  public Loader(
  CreateStreamChannelsService createStreamChannelsService,
  CreatePublisherService createPublisherService,
  CreatePodcastService createPodcastService,
  CreateAlbumService createAlbumService) {
    this.createStreamChannelsService = createStreamChannelsService;
    this.createPublisherService = createPublisherService;
    this.createPodcastService = createPodcastService;
    this.createAlbumService = createAlbumService;
  }
  
  @Override
	public void run(ApplicationArguments args) throws Exception {
    Yaml yaml = new Yaml();
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("db/seeds/start_loader.yml");

    if (inputStream == null) {
        throw new RuntimeException("Arquivo start_loader.yml não encontrado.");
    }

    System.out.println("Loading data from start_loader.yml");

    // Corrigir o tipo para Map
    Map<String, Object> data = yaml.load(inputStream);

    System.out.println("Creating publishers");
  
    Publisher publisher = new Publisher();
    publisher = createPublisherService.create(publisher);

    System.out.println("Creating stream channels");
  
    System.out.println(data.get("stream_chanels"));
    List<Map<String, Object>> stream_chanels = (List<Map<String, Object>>) data.get("stream_chanels");
    List<StreamChannel> streamChannels = new ArrayList<StreamChannel>();

    for (Map<String,Object> map : stream_chanels) {
      StreamChannel streamChannel = new StreamChannel();
      streamChannel.setName((String) map.get("name"));
      streamChannel.setDescription((String) map.get("description")); 
      streamChannel.setPublisher(publisher);
      streamChannel = createStreamChannelsService.create(streamChannel);
      streamChannels.add(streamChannel);
    }

    System.out.println("Creating podcasts");

    List<Map<String, Object>> podcasts = (List<Map<String, Object>>) data.get("podcasts");
    List<Podcast> Podcasts = new ArrayList<Podcast>();

    for (Map<String,Object> map : podcasts) {
      PodcastPresenter podcastPresenter = new PodcastPresenter();
      podcastPresenter.setTitle((String) map.get("title"));
      podcastPresenter.setDescription((String) map.get("description"));
      podcastPresenter.setReleaseDate((String) map.get("release_date"));
      podcastPresenter.setStreamChannelUuid(streamChannels.get(0).getUuid());
      
      File file = new File((String) map.get("media_path"));
      System.out.println(file.toPath());
      InputStream stream = new FileInputStream(file);
      
      Podcast podcast = podcastPresenter.toDomain();
      MultipartFile multipartFile = new MockMultipartFile(
        file.getName(), 
        file.getName(),
         "audio/mpeg",
        stream
        );

      podcast = createPodcastService.create(podcast, multipartFile);
      Podcasts.add(podcast);
    }
    
    System.out.println("Created Albums");

    List<Map<String, Object>> albums = (List<Map<String, Object>>) data.get("albums");
    List<Album> Albums = new ArrayList<Album>();

    for (Map<String,Object> map : albums) {
      AlbumPresenter albumPresenter = new AlbumPresenter();
      albumPresenter.setTitle((String) map.get("title"));
      albumPresenter.setDescription((String) map.get("description"));
      albumPresenter.setReleaseDate((String) map.get("release_date"));
      albumPresenter.setArtist((String) map.get("artist"));
      albumPresenter.setGenre((String) map.get("genre"));
      albumPresenter.setTracks((String) map.get("tracks"));
      albumPresenter.setYear((String) map.get("year"));
      albumPresenter.setStreamChannelUuid(streamChannels.get(1).getUuid());

      File file = new File((String) map.get("media_path"));
      InputStream stream = new FileInputStream(file);
      
      Album album = albumPresenter.toDomain();
      MultipartFile multipartFile = new MockMultipartFile(
        file.getName(), 
        file.getName(),
         "audio/mpeg",
        stream
        );
      MultipartFile[] files = {multipartFile};

      album = createAlbumService.create(album, files); 
      Albums.add(album);
    }
  }
}
