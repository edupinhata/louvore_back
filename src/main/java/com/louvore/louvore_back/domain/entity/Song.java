package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Song extends BaseEntity {
    @Column(nullable = false)
    private String title;
    private String artist;
    @Column(name = "original_key")
    private String originalKey;
    private Integer bpm;
    @Column(name = "time_signature")
    private String timeSignature;
    @Column(name = "theme_tags")
    private String themeTags;
    @Column(nullable = false)
    private boolean active = true;
}
