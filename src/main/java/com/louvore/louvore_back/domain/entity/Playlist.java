package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playlists")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Playlist extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_event_id", nullable = false)
    private ServiceEvent serviceEvent;
    @Column(nullable = false)
    private String name;
    private String notes;
}
