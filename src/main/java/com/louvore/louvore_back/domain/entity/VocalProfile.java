package com.louvore.louvore_back.domain.entity;

import com.louvore.louvore_back.domain.enums.SkillLevel;
import com.louvore.louvore_back.domain.enums.VoiceType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vocal_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VocalProfile extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;
    @Enumerated(EnumType.STRING)
    @Column(name = "voice_type", nullable = false)
    private VoiceType voiceType;
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;
}
