package com.podcast_streaming.gustavo_duarte.model.domain;

import lombok.Getter;
import lombok.Setter;

public class ListenerSubscription {
  @Getter @Setter private Integer id;
  @Getter @Setter private String uuid;
  @Getter @Setter private ClientListener podcastListener;
  @Getter @Setter private StreamChannel program;
}
