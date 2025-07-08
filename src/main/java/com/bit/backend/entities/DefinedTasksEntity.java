package com.bit.backend.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "defined_tasks")
public class DefinedTasksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "total_task_price")
    private Double totalTaskPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @OneToMany(mappedBy = "definedTasksEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DefinedSubTaskEntity> definedSubTaskEntities = new ArrayList<>();

    public DefinedTasksEntity() {
    }

    public DefinedTasksEntity(Long id, String taskName, Double totalTaskPrice, String description, String shortDescription, List<DefinedSubTaskEntity> definedSubTaskEntities) {
        this.id = id;
        this.taskName = taskName;
        this.totalTaskPrice = totalTaskPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.definedSubTaskEntities = definedSubTaskEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<DefinedSubTaskEntity> getDefinedSubTaskEntities() {
        return definedSubTaskEntities;
    }


    public Double getTotalTaskPrice() {
        return totalTaskPrice;
    }

    public void setTotalTaskPrice(Double totalTaskPrice) {
        this.totalTaskPrice = totalTaskPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDefinedSubTaskEntities(List<DefinedSubTaskEntity> definedSubTaskEntities) {
        this.definedSubTaskEntities.clear();
        if (definedSubTaskEntities != null) {
            definedSubTaskEntities.forEach(this::addDefinedSubTaskEntities);
        }
    }

    public void addDefinedSubTaskEntities(DefinedSubTaskEntity definedSubTaskEntity) {
        this.definedSubTaskEntities.add(definedSubTaskEntity);
        definedSubTaskEntity.setDefinedTasksEntity(this);
    }
}
