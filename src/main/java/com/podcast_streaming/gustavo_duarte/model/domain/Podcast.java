package com.podcast_streaming.gustavo_duarte.model.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "podcasts")
public class Podcast extends Content {
  private String uuid;
  private LocalDateTime releaseDate; 

  public Podcast(String uuid, String title, String description, LocalDateTime releaseDate, StreamChannel streamChannel) {
    super(null, title, description, streamChannel);
    this.uuid = uuid;
    this.releaseDate = releaseDate;
  } 

  public Podcast(String uuid, String title, String description, LocalDateTime releaseDate, String streamChannelUuid) {
    super(null, title, description, null);
    StreamChannel streamChannel = new StreamChannel();
    streamChannel.setUuid(streamChannelUuid);
    super.setStreamChannel(streamChannel);
    this.uuid = uuid;
    this.releaseDate = releaseDate;
  }
}
