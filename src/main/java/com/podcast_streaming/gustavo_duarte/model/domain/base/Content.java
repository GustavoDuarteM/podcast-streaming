package com.podcast_streaming.gustavo_duarte.model.domain.base;

import lombok.Getter;
import lombok.Setter;

public abstract class Content {
  @Getter @Setter private String uuid;
  @Getter @Setter private String name;
  @Getter @Setter private String description;
  @Getter @Setter private String release_date;
}
