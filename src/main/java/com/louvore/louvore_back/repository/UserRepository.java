package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameAndActiveTrue(String username);
}
