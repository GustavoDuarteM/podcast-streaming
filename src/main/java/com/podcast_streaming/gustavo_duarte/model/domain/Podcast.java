package com.podcast_streaming.gustavo_duarte.model.domain;

import com.podcast_streaming.gustavo_duarte.model.domain.base.Content;

import lombok.Getter;
import lombok.Setter;

public class Podcast extends Content {
  @Getter @Setter private Integer id;
  @Getter @Setter private String uuid;
  @Getter @Setter private Media media;
}
