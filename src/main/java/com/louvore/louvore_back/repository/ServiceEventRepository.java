package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.ServiceEvent;
import com.louvore.louvore_back.domain.enums.ServiceEventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ServiceEventRepository extends JpaRepository<ServiceEvent, UUID> {
    Page<ServiceEvent> findByChurchId(UUID churchId, Pageable pageable);
    Page<ServiceEvent> findByChurchIdAndStatus(UUID churchId, ServiceEventStatus status, Pageable pageable);
}
