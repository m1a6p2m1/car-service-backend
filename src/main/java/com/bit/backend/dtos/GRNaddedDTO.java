package com.bit.backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;


public class GRNaddedDTO {
    private Long id;
    private Long grnno;
    private int qty;
    private BigDecimal ucost;
    private BigDecimal cost;
    private String item;
    private int itemID;
    private LocalDate expdate;

    public GRNaddedDTO(Long id, Long grnno, int qty, BigDecimal ucost, BigDecimal cost, String item, int itemID, LocalDate expdate) {
        this.id = id;
        this.grnno = grnno;
        this.qty = qty;
        this.ucost = ucost;
        this.cost = cost;
        this.item = item;
        this.itemID = itemID;
        this.expdate = expdate;
    }

    public GRNaddedDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrnno() {
        return grnno;
    }

    public void setGrnno(Long grnno) {
        this.grnno = grnno;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUcost() {
        return ucost;
    }

    public void setUcost(BigDecimal ucost) {
        this.ucost = ucost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public LocalDate getExpdate() {
        return expdate;
    }

    public void setExpdate(LocalDate expdate) {
        this.expdate = expdate;
    }
}
