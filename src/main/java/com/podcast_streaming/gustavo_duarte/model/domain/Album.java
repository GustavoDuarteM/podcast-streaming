package com.podcast_streaming.gustavo_duarte.model.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
  
  @NotBlank(message = "Nome do álbum é obrigatório")
  private String artist;
  
  @NotBlank(message = "Gênero do álbum é obrigatório")
  private String genre;

  @Positive(message = "Número de faixas do álbum é obrigatório")
  private Integer tracks;
  
  @Positive(message = "Ano de lançamento do álbum é obrigatório")
  private Integer year;

  @NotBlank(message = "Data de lançamento do álbum é obrigatória")
  private LocalDateTime releaseDate;
}
