package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.Ministry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MinistryRepository extends JpaRepository<Ministry, UUID> {
    Page<Ministry> findByChurchId(UUID churchId, Pageable pageable);
}
