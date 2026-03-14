package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
    Page<Song> findByActiveTrue(Pageable pageable);
    Page<Song> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
