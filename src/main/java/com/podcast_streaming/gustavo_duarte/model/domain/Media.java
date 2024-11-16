package com.podcast_streaming.gustavo_duarte.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "medias")
public class Media {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;
  
  private String uuid;
  
  @NotBlank(message = "Nome do arquivo é obrigatório")
  private String fileName;
  
  @NotBlank(message = "URL do arquivo é obrigatória")
  @Column(name = "file_url")
  private String fileURL;

  @NotBlank(message = "Chave do arquivo é obrigatória")
  private String fileKey;
  
  @NotBlank(message = "Extensão do arquivo é obrigatória")
  private String fileExtension;

  @NotNull(message = "Duração do arquivo é obrigatória")
  private Integer durationTimeSeconds;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "content_id", referencedColumnName = "id")
  private Content content;
}
