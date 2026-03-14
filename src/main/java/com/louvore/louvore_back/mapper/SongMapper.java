package com.louvore.louvore_back.mapper;

import com.louvore.louvore_back.domain.entity.Song;
import com.louvore.louvore_back.dto.request.SongRequest;
import com.louvore.louvore_back.dto.response.SongResponse;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    public Song toEntity(SongRequest request) {
        return Song.builder()
                .title(request.title()).artist(request.artist()).originalKey(request.originalKey())
                .bpm(request.bpm()).timeSignature(request.timeSignature()).themeTags(request.themeTags())
                .active(request.active() != null ? request.active() : true)
                .build();
    }
    public SongResponse toResponse(Song song) {
        return new SongResponse(song.getId(), song.getTitle(), song.getArtist(), song.getOriginalKey(),
                song.getBpm(), song.getTimeSignature(), song.getThemeTags(), song.isActive(),
                song.getCreatedAt(), song.getUpdatedAt());
    }
    public void updateEntity(Song song, SongRequest request) {
        song.setTitle(request.title());
        song.setArtist(request.artist());
        song.setOriginalKey(request.originalKey());
        song.setBpm(request.bpm());
        song.setTimeSignature(request.timeSignature());
        song.setThemeTags(request.themeTags());
        if (request.active() != null) song.setActive(request.active());
    }
}
