package com.bit.backend.repositories;

import com.bit.backend.entities.PasswordResetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetEntity, Long> {
    PasswordResetEntity findByToken(String token);
}
