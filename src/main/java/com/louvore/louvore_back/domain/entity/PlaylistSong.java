package com.louvore.louvore_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playlist_songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistSong extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;
    @Column(name = "order_index", nullable = false)
    private int orderIndex;
    @Column(name = "chosen_key")
    private String chosenKey;
    private String notes;
}
