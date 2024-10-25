package com.podcast_streaming.gustavo_duarte.model.presenter;

import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;

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
    return new Podcast(
      this.uuid,
      this.title,
      this.description,
      releaseDateTime,
      this.streamChannelUuid
      );
  }
}
