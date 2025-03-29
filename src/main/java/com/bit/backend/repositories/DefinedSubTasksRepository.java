package com.bit.backend.repositories;

import com.bit.backend.entities.DefinedSubTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefinedSubTasksRepository extends JpaRepository<DefinedSubTaskEntity, Long> {
}
