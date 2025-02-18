package com.bit.backend.repositories;

import com.bit.backend.entities.CustomerEntity;;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
