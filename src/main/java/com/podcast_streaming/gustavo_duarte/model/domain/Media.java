package com.podcast_streaming.gustavo_duarte.model.domain;

import lombok.Getter;
import lombok.Setter;

public class Media {
  @Getter @Setter private Integer id;
  @Getter @Setter private String uuid;
  @Getter @Setter private StreamChannel author;
  @Getter @Setter private String title;
  @Getter @Setter private Integer durationTimeSeconds;
}
