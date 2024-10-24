package com.podcast_streaming.gustavo_duarte.model.presenter;

import com.podcast_streaming.gustavo_duarte.model.domain.Publisher;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StreamChannelPresenter {
  private Integer id;
  private String uuid;
  private String name;
  private String description; 
  private String publisherUuid;
  

  public StreamChannel toDomain() {
    return new StreamChannel(
      this.id,
      this.uuid,
      this.name,
      this.description,
      new Publisher(
        null,
        this.publisherUuid,
        null
      ),
      null
    );
  }
}
