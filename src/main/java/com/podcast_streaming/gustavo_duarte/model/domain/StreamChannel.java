package com.podcast_streaming.gustavo_duarte.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

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
  private Integer id;
  private String uuid;
  private String name;
  private String description; 
  private LocalDateTime createdAt;
  
  @JsonIgnore
  @ManyToOne(optional = true)
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;
}
