package de.ksuzy.todolist.mapper;

import de.ksuzy.todolist.database.entity.Task;
import de.ksuzy.todolist.dto.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toDto(Task task);

    Task toEntity(TaskDTO dto);
}
