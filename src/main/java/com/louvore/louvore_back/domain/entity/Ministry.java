package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ministries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ministry extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "church_id", nullable = false)
    private Church church;
}
