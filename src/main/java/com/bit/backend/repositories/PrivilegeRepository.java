package com.bit.backend.repositories;

import com.bit.backend.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Query(nativeQuery = true, value = "SELECT  * FROM get_available_system_auth_details WHERE assigned = 0")
    Optional<Privilege> getAvailableSystemPrivileges();

    @Query(nativeQuery = true, value = "SELECT * FROM get_available_system_auth_details WHERE assigned = 1")
    Optional<Privilege> getAssignedSystemPrivileges();

    Optional<Privilege> findByAuthId(int authId);
}
