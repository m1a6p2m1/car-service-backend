package com.bit.backend.repositories;

import com.bit.backend.dtos.AuthDto;
import com.bit.backend.dtos.SystemPrivilegeDto;
import com.bit.backend.dtos.SystemPrivilegeListDto;
import com.bit.backend.entities.User;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    @Query(nativeQuery = true, value = "select auth_details.auth_id as auth_id, :userId as user_id from " +
                                       "(select * from auth_group_users auth_users where auth_users.user_Id = :userId) auth_users\n" +
                                       "join (select * from ems.auth_group_authentication auth_details) as auth_details\n" +
                                       "on auth_users.auth_group_id = auth_details.auth_group_id\n" +
                                       "group by auth_details.auth_id")
    Optional<List<Integer>> findAuthIdsByUserId(@Param("userId") long userId);


    @Query(nativeQuery = true, value = "SELECT id, description FROM get_available_system_auth_details WHERE assigned = 0")
    List<Tuple> getAvailableSystemPrivileges();

    @Query(nativeQuery = true, value = "SELECT id, description FROM get_available_system_auth_details WHERE assigned = 1")
    List<Tuple> getAssignedSystemPrivileges();
}
