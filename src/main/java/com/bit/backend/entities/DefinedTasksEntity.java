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

    @OneToMany(mappedBy = "definedTasksEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DefinedSubTaskEntity> definedSubTaskEntities = new ArrayList<>();

    @Column(name = "total_task_price")
    private String totalTaskPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    public DefinedTasksEntity() {
    }

    public DefinedTasksEntity(Long id, String taskName, List<DefinedSubTaskEntity> definedSubTaskEntities, String totalTaskPrice, String description, String shortDescription) {
        this.id = id;
        this.taskName = taskName;
        this.definedSubTaskEntities = definedSubTaskEntities;
        this.totalTaskPrice = totalTaskPrice;
        this.description = description;
        this.shortDescription = shortDescription;
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

    public String getTotalTaskPrice() {
        return totalTaskPrice;
    }

    public void setTotalTaskPrice(String totalTaskPrice) {
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
