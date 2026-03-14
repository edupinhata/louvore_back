package com.louvore.louvore_back.repository;

import com.louvore.louvore_back.domain.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {
}
