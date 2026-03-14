package com.louvore.louvore_back.domain.entity;

import com.louvore.louvore_back.domain.enums.ServiceEventStatus;
import com.louvore.louvore_back.domain.enums.ServiceEventType;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "service_events")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceEvent extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "church_id", nullable = false)
    private Church church;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ministry_id")
    private Ministry ministry;
    @Column(nullable = false)
    private String title;
    @Column(name = "date_time", nullable = false)
    private Instant dateTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceEventType type;
    private String theme;
    private String notes;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceEventStatus status = ServiceEventStatus.PLANNED;
}
