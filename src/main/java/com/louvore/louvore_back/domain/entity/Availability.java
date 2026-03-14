package com.louvore.louvore_back.domain.entity;

import com.louvore.louvore_back.domain.enums.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "availabilities")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Availability extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_event_id", nullable = false)
    private ServiceEvent serviceEvent;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus status;
    private String notes;
}
