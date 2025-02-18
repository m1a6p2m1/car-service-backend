package com.bit.backend.repositories;

import com.bit.backend.entities.PrivilegeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrivilegeGroupRepository extends JpaRepository<PrivilegeGroup, Long> {

    List<PrivilegeGroup> findAll();
    Optional<List<PrivilegeGroup>> findByGroupName(String groupName);
    Optional<PrivilegeGroup> findById(int id);
    @Query(nativeQuery = true, value = "SELECT * FROM auth_groups WHERE status = 1")
    List<PrivilegeGroup> getActivePrivilageGroups();
    @Query(nativeQuery = true, value = "SELECT * FROM auth_groups WHERE id != :id AND group_name = :name")
    Optional<List<PrivilegeGroup>> findByIdAndName(@Param("id") long id, @Param("name") String name);
    @Query(nativeQuery = true, value = "SELECT * FROM auth_groups WHERE group_name = :name and  status = 1")
    Optional<List<PrivilegeGroup>> findByGroupNameAndStatus( @Param("name") String name);
}
