package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.Church;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ChurchRepository extends JpaRepository<Church, UUID> {
    Page<Church> findByActiveTrue(Pageable pageable);
}
