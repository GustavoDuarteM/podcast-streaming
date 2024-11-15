package com.podcast_streaming.gustavo_duarte.model.presenter;


import com.podcast_streaming.gustavo_duarte.model.domain.Album;
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
public class AlbumPresenter {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private String uuid;
  private String title;
  private String description;
  private String releaseDate;
  private String artist;
  private String genre;
  private Integer tracks;
  private Integer year;
  private String streamChannelUuid;
  
  public Album toDomain() {
    LocalDate releaseDate = LocalDate.parse(this.releaseDate, FORMATTER);
    LocalDateTime releaseDateTime = releaseDate.atStartOfDay();
    
    StreamChannel streamChannel = new StreamChannel();
    streamChannel.setUuid(this.streamChannelUuid);
    
    Album album = new Album();
    album.setUuid(this.uuid);
    album.setTitle(this.title);
    album.setDescription(this.description);
    album.setReleaseDate(releaseDateTime);
    album.setArtist(description);
    album.setGenre(this.genre);
    album.setTracks(this.tracks);
    album.setYear(this.year);
    album.setStreamChannel(streamChannel);

    return album;
  }
}
