package com.podcast_streaming.gustavo_duarte.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "stream_channels")
public class StreamChannel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;
  private String uuid;
  
  @NotBlank(message = "Nome do canal é obrigatório")
  private String name;

  
  private String description; 
  
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "publisher_id", referencedColumnName = "id")
  private Publisher publisher;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JoinColumn(name = "stream_channel_id")
  private List<Content> contents;
  
}
