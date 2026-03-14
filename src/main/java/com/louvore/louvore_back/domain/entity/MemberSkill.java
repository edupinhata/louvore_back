package com.louvore.louvore_back.domain.entity;

import com.louvore.louvore_back.domain.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_skills")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberSkill extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrument_id", nullable = false)
    private Instrument instrument;
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;
    @Column(nullable = false)
    private boolean preferred = false;
}
