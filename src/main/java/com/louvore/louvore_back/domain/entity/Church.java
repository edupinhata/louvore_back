package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "churches")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Church extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String city;
    private String state;
    @Column(nullable = false)
    private boolean active = true;
}
