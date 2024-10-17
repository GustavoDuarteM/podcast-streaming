package com.podcast_streaming.gustavo_duarte.model.domain;

import com.podcast_streaming.gustavo_duarte.model.domain.base.Content;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;  
import java.time.LocalDateTime;

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
  private Publisher publisher;
  private List<Content> contents;
  private String name;
  private String description; 
  private LocalDateTime createdAt;
}
