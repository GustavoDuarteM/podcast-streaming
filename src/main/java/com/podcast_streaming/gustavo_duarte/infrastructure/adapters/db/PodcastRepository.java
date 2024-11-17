package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.podcast_streaming.gustavo_duarte.model.domain.Podcast;

@Repository
public interface PodcastRepository extends CrudRepository<Podcast, Long>, PagingAndSortingRepository<Podcast, Long> {
    Podcast findByUuid(String uuid);
    Podcast findBystreamChannelIdAndUuid(Integer streamChannelId, String uuid);
    Iterable<Podcast> findBystreamChannelId(Integer streamChannelId);
    List<Podcast> findBystreamChannelId(Pageable pageable, Integer streamChannelId);
    void deleteBystreamChannelIdAndUuid(Integer streamChannelId, String uuid);
}
