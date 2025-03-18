package com.bit.backend.repositories;

import com.bit.backend.entities.AllTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllTaskRepository extends JpaRepository<AllTaskEntity, Long> {
}
