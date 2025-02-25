package com.bit.backend.repositories;

import com.bit.backend.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
