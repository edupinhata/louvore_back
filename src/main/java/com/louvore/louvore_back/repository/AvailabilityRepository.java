package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {
    List<Availability> findByServiceEventId(UUID serviceEventId);
    Optional<Availability> findByMemberIdAndServiceEventId(UUID memberId, UUID serviceEventId);
}
