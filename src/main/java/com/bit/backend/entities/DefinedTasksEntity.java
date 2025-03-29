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

    public DefinedTasksEntity() {
    }

    public DefinedTasksEntity(Long id, String taskName) {
        this.id = id;
        this.taskName = taskName;
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
