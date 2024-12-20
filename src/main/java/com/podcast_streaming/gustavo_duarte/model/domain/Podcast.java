package com.podcast_streaming.gustavo_duarte.model.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

  @NotNull(message = "Data de lançamento do álbum é obrigatória")
  private LocalDateTime releaseDate; 
}
