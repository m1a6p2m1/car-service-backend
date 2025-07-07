package com.bit.backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GRNDTO {


    private Long grnno;
    private String supplier;
    private BigDecimal tcost;
    private LocalDate addedDate;
    private String addedUser;

    public GRNDTO() {
    }

    public GRNDTO(Long grnno, String supplier, BigDecimal tcost, LocalDate addedDate, String addedUser) {
        this.grnno = grnno;
        this.supplier = supplier;
        this.tcost = tcost;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getTcost() {
        return tcost;
    }

    public void setTcost(BigDecimal tcost) {
        this.tcost = tcost;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }
}
