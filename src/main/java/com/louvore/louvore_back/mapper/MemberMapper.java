package com.louvore.louvore_back.mapper;

import com.louvore.louvore_back.domain.entity.Member;
import com.louvore.louvore_back.dto.response.MemberResponse;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(), member.getFullName(), member.getEmail(), member.getPhone(),
                member.isActive(),
                member.getChurch().getId(), member.getChurch().getName(),
                member.getCreatedAt(), member.getUpdatedAt());
    }
}
