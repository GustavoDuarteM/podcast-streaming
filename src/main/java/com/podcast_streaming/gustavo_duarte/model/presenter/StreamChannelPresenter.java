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
    Publisher publisher = new Publisher();
    publisher.setUuid(this.publisherUuid);
    
    StreamChannel streamChannel = new StreamChannel();
    streamChannel.setId(this.id);
    streamChannel.setUuid(this.uuid);
    streamChannel.setDescription(this.description);
    streamChannel.setPublisher(publisher);

    return streamChannel;
  }
}
