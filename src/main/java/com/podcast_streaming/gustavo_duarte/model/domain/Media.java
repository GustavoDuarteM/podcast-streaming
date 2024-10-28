package com.podcast_streaming.gustavo_duarte.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
  private String fileName;
  private String fileURL;
  private String fileKey;
  private String fileExtension;
  private Integer durationTimeSeconds;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "content_id", referencedColumnName = "id")
  private Content content;
}
