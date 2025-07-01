package com.bit.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="grn_added_table")
public class GRNaddedEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnore
    @Column(name = "grnno")
    private Long grnno;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "ucost")
    private BigDecimal ucost;

    @Column(name = "qty")
    private int qty;

    @Column(name = "item")
    private String item;

    @Column(name = "itemID")
    private int itemID;

    @Column(name = "expdate")
    private LocalDate expdate;

    public GRNaddedEntitiy() {
    }

    public GRNaddedEntitiy(Long id, Long grnno, BigDecimal cost, BigDecimal ucost, int qty, String item, int itemID, LocalDate expdate) {
        this.id = id;
        this.grnno = grnno;
        this.cost = cost;
        this.ucost = ucost;
        this.qty = qty;
        this.item = item;
        this.itemID = itemID;
        this.expdate = expdate;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getUcost() {
        return ucost;
    }

    public void setUcost(BigDecimal ucost) {
        this.ucost = ucost;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
