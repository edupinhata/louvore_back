package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.MemberSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MemberSkillRepository extends JpaRepository<MemberSkill, UUID> {
    List<MemberSkill> findByMemberId(UUID memberId);
}
