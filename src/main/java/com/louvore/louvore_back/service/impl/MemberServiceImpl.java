package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.domain.entity.Church;
import com.louvore.louvore_back.domain.entity.Member;
import com.louvore.louvore_back.dto.request.MemberRequest;
import com.louvore.louvore_back.dto.response.MemberResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.exception.ResourceNotFoundException;
import com.louvore.louvore_back.mapper.MemberMapper;
import com.louvore.louvore_back.repository.ChurchRepository;
import com.louvore.louvore_back.repository.MemberRepository;
import com.louvore.louvore_back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ChurchRepository churchRepository;
    private final MemberMapper memberMapper;

    @Override
    public MemberResponse create(MemberRequest request) {
        Church church = churchRepository.findById(request.churchId())
                .orElseThrow(() -> new ResourceNotFoundException("Church", request.churchId()));
        Member member = Member.builder()
                .fullName(request.fullName()).email(request.email()).phone(request.phone())
                .active(request.active() != null ? request.active() : true)
                .church(church).build();
        return memberMapper.toResponse(memberRepository.save(member));
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponse findById(UUID id) {
        return memberRepository.findById(id)
                .map(memberMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Member", id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<MemberResponse> findByChurch(UUID churchId, Pageable pageable) {
        return PageResponse.from(memberRepository.findByChurchId(churchId, pageable).map(memberMapper::toResponse));
    }

    @Override
    public MemberResponse update(UUID id, MemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", id));
        Church church = churchRepository.findById(request.churchId())
                .orElseThrow(() -> new ResourceNotFoundException("Church", request.churchId()));
        member.setFullName(request.fullName());
        member.setEmail(request.email());
        member.setPhone(request.phone());
        if (request.active() != null) member.setActive(request.active());
        member.setChurch(church);
        return memberMapper.toResponse(memberRepository.save(member));
    }

    @Override
    public void delete(UUID id) {
        if (!memberRepository.existsById(id)) throw new ResourceNotFoundException("Member", id);
        memberRepository.deleteById(id);
    }
}
