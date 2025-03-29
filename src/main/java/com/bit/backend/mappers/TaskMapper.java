package com.bit.backend.mappers;

import com.bit.backend.dtos.SubTaskDto;
import com.bit.backend.dtos.TaskDto;
import com.bit.backend.entities.SubTask;
import com.bit.backend.entities.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TaskMapper {
    @Mapping(source = "subTasks", target = "subTasks")
    TaskDto toTaskDto(Task task);

    @Mapping(source = "subTasks", target = "subTasks")
    Task toTaskEntity(TaskDto taskDto);

    SubTask toSubTaskEntity(SubTaskDto subTaskDTO);

    SubTaskDto toSubTaskDto(SubTask subTask);

    List<SubTask> toSubTaskEntityList(List<SubTaskDto> subTaskDtos);
    List<SubTaskDto> toSubTaskDtoList(List<SubTask> subTasks);

    @AfterMapping
    default void linkSubTasks(@MappingTarget Task task) {
        if (task.getSubTasks() != null) {
            task.getSubTasks().forEach(subTask -> subTask.setTask(task));
        }
    }
}
