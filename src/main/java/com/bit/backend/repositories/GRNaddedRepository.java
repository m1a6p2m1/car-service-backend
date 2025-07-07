package com.bit.backend.repositories;

import com.bit.backend.entities.GRNaddedEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GRNaddedRepository extends JpaRepository<GRNaddedEntitiy, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM grn_added_table WHERE grnno = :grnno")
    List<GRNaddedEntitiy> findAllByGRNNO(@Param("grnno") long grnno);

    @Query(value = "SELECT SUM(qty) FROM ems.grn_added_table WHERE itemid = :itemId", nativeQuery = true)
    Integer findSumOfQtyByItemId(@Param("itemId") int itemId);
}
