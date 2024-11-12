package com.podcast_streaming.gustavo_duarte.model.presenter;


import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PodcastPresenter {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private String uuid;
  private String title;
  private String description;
  private String releaseDate;
  private String streamChannelUuid;
  
  public Podcast toDomain() {
    LocalDate releaseDate = LocalDate.parse(this.releaseDate, FORMATTER);
    LocalDateTime releaseDateTime = releaseDate.atStartOfDay();
    
    StreamChannel streamChannel = new StreamChannel();
    streamChannel.setUuid(this.streamChannelUuid);
    
    Podcast podcast = new Podcast();
    podcast.setUuid(this.uuid);
    podcast.setTitle(this.title);
    podcast.setDescription(this.description);
    podcast.setReleaseDate(releaseDateTime);
    podcast.setStreamChannel(streamChannel);

    return podcast;
  }
}
