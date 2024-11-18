package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.podcast_streaming.gustavo_duarte.model.domain.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>, PagingAndSortingRepository<Album, Long> {
    Album findByUuid(String uuid);
    Album findBystreamChannelIdAndUuid(Integer streamChannelId, String uuid);
    Iterable<Album> findBystreamChannelId(Integer streamChannelId);
    List<Album> findBystreamChannelId(Pageable pageable, Integer streamChannelId);
    void deleteBystreamChannelIdAndUuid(Integer streamChannelId, String uuid);
}