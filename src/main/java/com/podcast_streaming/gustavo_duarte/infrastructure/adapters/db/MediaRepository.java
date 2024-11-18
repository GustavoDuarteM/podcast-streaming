package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.db;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.podcast_streaming.gustavo_duarte.model.domain.Media;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {
  void deleteAllByContentId(Integer contentId);
}
