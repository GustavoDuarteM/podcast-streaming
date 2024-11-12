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
@Table(name = "albums")
public class Album extends Content {
  private String uuid;
  private String artist;
  private String genre;
  private Integer tracks;
  private Integer year;
  private LocalDateTime releaseDate;
}
