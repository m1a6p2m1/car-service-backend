package com.bit.backend.repositories;

import com.bit.backend.entities.DefinedTasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefinedTasksRepository extends JpaRepository<DefinedTasksEntity, Long> {
}
