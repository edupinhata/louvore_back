package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Member extends BaseEntity {
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phone;
    @Column(nullable = false)
    private boolean active = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "church_id", nullable = false)
    private Church church;
}
