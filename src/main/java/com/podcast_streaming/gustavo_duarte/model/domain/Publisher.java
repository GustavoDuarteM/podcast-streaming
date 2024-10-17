package com.podcast_streaming.gustavo_duarte.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "publishers")

public class Publisher {
  @Id
  private Integer id;
  private String uuid;
  private List<StreamChannel> streamChannels;
}
