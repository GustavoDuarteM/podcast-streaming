package com.podcast_streaming.gustavo_duarte.model.domain;

import com.podcast_streaming.gustavo_duarte.model.domain.base.Content;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Album extends Content {
  @Getter @Setter private Integer id;
  @Getter @Setter private String uuid;
  @Getter @Setter private List<Media> media;
}
