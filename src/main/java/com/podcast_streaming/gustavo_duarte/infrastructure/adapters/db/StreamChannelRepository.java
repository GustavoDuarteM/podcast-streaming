package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;

@Repository
public interface StreamChannelRepository extends CrudRepository<StreamChannel, Long> {
    StreamChannel findByUuid(String uuid);
}