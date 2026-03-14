package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scheduling_constraints")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SchedulingConstraint extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "church_id", nullable = false)
    private Church church;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private boolean enabled = true;
    @Column(name = "constraint_type")
    private String constraintType;
}
