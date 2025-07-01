package com.bit.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="grn_summary")
public class GRNEntitiy {

    @Id
    @Column(name = "grnno")
    private Long grnno;

    @Column(name = "tcost")
    private BigDecimal tcost;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "addedDate")
    private LocalDate addedDate;

    @Column(name = "addedUser")
    private String addedUser;



    public GRNEntitiy() {
    }

    public GRNEntitiy(Long grnno, BigDecimal tcost, String supplier, LocalDate addedDate, String addedUser) {
        this.grnno = grnno;
        this.tcost = tcost;
        this.supplier = supplier;
        this.addedDate = addedDate;
        this.addedUser = addedUser;
    }

    public String getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(String addedUser) {
        this.addedUser = addedUser;
    }

    public Long getGrnno() {
        return grnno;
    }

    public void setGrnno(Long grnno) {
        this.grnno = grnno;
    }

    public BigDecimal getTcost() {
        return tcost;
    }

    public void setTcost(BigDecimal tcost) {
        this.tcost = tcost;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }
}
