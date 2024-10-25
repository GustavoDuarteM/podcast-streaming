package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;

@Repository
public interface PodcastRepository extends CrudRepository<Podcast, Long> {
    Podcast findByUuid(String uuid);
    Podcast findBystreamChannelIdAndUuid(Integer streamChannelId, String uuid);
    Iterable<Podcast> findBystreamChannelId(Integer streamChannelId);
}
