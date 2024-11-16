package com.podcast_streaming.gustavo_duarte.model.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "contents")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;
  
  @NotBlank(message = "Titulo é obrigatório")
  private String title;
  @NotBlank(message = "Descrição é obrigatória")
  
  @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres")
  private String description;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "stream_channel_id")
  private StreamChannel streamChannel;


  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JoinColumn(name = "content_id")
  private List<Media> Media;
}
