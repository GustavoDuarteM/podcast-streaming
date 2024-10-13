package com.podcast_streaming.gustavo_duarte.model.domain;

import com.podcast_streaming.gustavo_duarte.model.domain.base.Content;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class StreamChannel {
  @Getter @Setter private Integer id;
  @Getter @Setter private String uuid;
  @Getter @Setter private Publisher publisher;
  @Getter @Setter private List<Content> contents;
  @Getter @Setter private String name;
  @Getter @Setter private String description; 
}
