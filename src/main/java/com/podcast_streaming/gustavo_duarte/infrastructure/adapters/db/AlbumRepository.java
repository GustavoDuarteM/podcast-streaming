package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.podcast_streaming.gustavo_duarte.model.domain.Album;


@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    Album findByUuid(String uuid);
    Iterable<Album> findBystreamChannelId(Integer streamChannelId);
}