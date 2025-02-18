package com.bit.backend.repositories;

import com.bit.backend.entities.PrivilegeGroupAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrivilegeGroupAuthRepository extends JpaRepository<PrivilegeGroupAuth, Long> {

    PrivilegeGroupAuth findById(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM auth_group_authentication WHERE auth_group_id = :authGroupId AND auth_id = :authId")
    PrivilegeGroupAuth findByAuthGroupIdAndAuthId(@Param("authGroupId") int authGroupId ,@Param("authId") int authId);
}
