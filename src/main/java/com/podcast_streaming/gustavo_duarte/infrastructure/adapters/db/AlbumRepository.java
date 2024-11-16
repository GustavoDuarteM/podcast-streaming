package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.podcast_streaming.gustavo_duarte.model.domain.Album;


@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>, PagingAndSortingRepository<Album, Long> {
    Album findByUuid(String uuid);
    Iterable<Album> findBystreamChannelId(Integer streamChannelId);
    List<Album> findBystreamChannelId(Pageable pageable, Integer streamChannelId);
}