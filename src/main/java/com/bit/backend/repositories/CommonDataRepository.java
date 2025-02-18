package com.bit.backend.repositories;

import com.bit.backend.entities.CommonDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommonDataRepository extends JpaRepository<CommonDataEntity, Long> {

    @Query(nativeQuery = true, value = "select sys_auth.auth_id as id, sys_auth.auth_description as description from (" +
                                       "select * from system_authentications as sys_auth where sys_auth.assigned = 1) as sys_auth " +
                                       "LEFT JOIN (select * from auth_group_authentication as auth_group where auth_group.auth_group_id = :groupId) as auth_group " +
                                       "ON sys_auth.auth_id = auth_group.auth_id WHERE auth_group.auth_id is null")
    List<CommonDataEntity> getAvailablePrivilegesByGroupId(int groupId);

    @Query(nativeQuery = true, value = "SELECT auth_id as id, description FROM get_assigned_auths WHERE group_id = :groupId")
    List<CommonDataEntity> getAssignedPrivilegesByGroupId(int groupId);

    @Query(nativeQuery = true, value = "select users.id as id, CONCAT(users.first_name, ' ', users.last_name) AS description from app_user as users\n" +
                                       "left join (select * from auth_group_users where auth_group_id = :groupId) as auth_group\n" +
                                       "on users.id = auth_group.user_id WHERE auth_group.auth_group_id is null")
    List<CommonDataEntity> getAvailableUsersByGroupID(int groupId);

    @Query(nativeQuery = true, value = "SELECT id, description FROM get_auth_group_user_details WHERE auth_group_id = :groupId")
    List<CommonDataEntity> getAssignedUsersByGroupId(int groupId);
}
