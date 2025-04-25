package com.bit.backend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "defined_sub_tasks")
public class DefinedSubTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_task_name")
    private String subTaskName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private DefinedTasksEntity definedTasksEntity;

    @Column(name = "price")
    private BigDecimal price;

    public DefinedSubTaskEntity() {
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DefinedSubTaskEntity(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public DefinedTasksEntity getDefinedTasksEntity() {
        return definedTasksEntity;
    }

    public void setDefinedTasksEntity(DefinedTasksEntity definedTasksEntity) {
        this.definedTasksEntity = definedTasksEntity;
    }
}
