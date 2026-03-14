package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.domain.entity.Song;
import com.louvore.louvore_back.dto.request.SongRequest;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.dto.response.SongResponse;
import com.louvore.louvore_back.exception.ResourceNotFoundException;
import com.louvore.louvore_back.mapper.SongMapper;
import com.louvore.louvore_back.repository.SongRepository;
import com.louvore.louvore_back.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Override
    public SongResponse create(SongRequest request) {
        return songMapper.toResponse(songRepository.save(songMapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public SongResponse findById(UUID id) {
        return songRepository.findById(id)
                .map(songMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Song", id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SongResponse> findAll(Pageable pageable) {
        return PageResponse.from(songRepository.findAll(pageable).map(songMapper::toResponse));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<SongResponse> search(String title, Pageable pageable) {
        return PageResponse.from(songRepository.findByTitleContainingIgnoreCase(title, pageable).map(songMapper::toResponse));
    }

    @Override
    public SongResponse update(UUID id, SongRequest request) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song", id));
        songMapper.updateEntity(song, request);
        return songMapper.toResponse(songRepository.save(song));
    }

    @Override
    public void delete(UUID id) {
        if (!songRepository.existsById(id)) throw new ResourceNotFoundException("Song", id);
        songRepository.deleteById(id);
    }
}
