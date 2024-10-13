package com.podcast_streaming.gustavo_duarte.model.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Publisher {
  @Getter @Setter private Integer id;
  @Getter @Setter private String uuid;
  @Getter @Setter private List<StreamChannel> streamChannels;
}
