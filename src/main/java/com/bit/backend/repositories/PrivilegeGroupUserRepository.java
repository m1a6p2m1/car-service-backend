package com.bit.backend.repositories;

import com.bit.backend.entities.PrivilegeGroupAuth;
import com.bit.backend.entities.PrivilegeGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrivilegeGroupUserRepository extends JpaRepository<PrivilegeGroupUser, Long> {

    PrivilegeGroupUser findById(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM auth_group_users WHERE auth_group_id = :authGroupId AND user_id = :userId")
    PrivilegeGroupUser findByAuthGroupIdAndUserId(@Param("authGroupId") int authGroupId , @Param("userId") int authId);
}
