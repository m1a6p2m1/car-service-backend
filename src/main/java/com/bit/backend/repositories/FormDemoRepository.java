package com.bit.backend.repositories;

import com.bit.backend.entities.FormDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormDemoRepository extends JpaRepository<FormDemoEntity, Long> {
}
