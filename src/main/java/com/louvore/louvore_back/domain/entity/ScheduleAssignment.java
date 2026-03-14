package com.louvore.louvore_back.domain.entity;

import com.louvore.louvore_back.domain.enums.AssignmentRole;
import com.louvore.louvore_back.domain.enums.AssignmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedule_assignments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ScheduleAssignment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_event_id", nullable = false)
    private ServiceEvent serviceEvent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentRole role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status = AssignmentStatus.SUGGESTED;
    @Column(name = "generated_automatically", nullable = false)
    private boolean generatedAutomatically = false;
}
