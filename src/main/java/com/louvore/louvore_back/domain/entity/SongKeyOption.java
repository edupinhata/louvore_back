package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song_key_options")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongKeyOption extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;
    @Column(name = "key_name", nullable = false)
    private String keyName;
    private String notes;
}
