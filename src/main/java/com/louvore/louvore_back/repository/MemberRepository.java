package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Page<Member> findByChurchId(UUID churchId, Pageable pageable);
    Page<Member> findByChurchIdAndActiveTrue(UUID churchId, Pageable pageable);
}
