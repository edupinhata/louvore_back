package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.ScheduleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ScheduleAssignmentRepository extends JpaRepository<ScheduleAssignment, UUID> {
    List<ScheduleAssignment> findByServiceEventId(UUID serviceEventId);
    List<ScheduleAssignment> findByMemberId(UUID memberId);
}
