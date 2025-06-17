package com.bit.backend.repositories;

import com.bit.backend.entities.OnlineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineItemRepository extends JpaRepository<OnlineItemEntity, Long> {
}
