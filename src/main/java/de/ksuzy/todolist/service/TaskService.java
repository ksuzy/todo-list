package de.ksuzy.todolist.service;

import de.ksuzy.todolist.database.entity.Task;
import de.ksuzy.todolist.database.repository.TaskRepository;
import de.ksuzy.todolist.dto.TaskDTO;
import de.ksuzy.todolist.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Transactional
    public TaskDTO create(TaskDTO dto) {
        Task task = mapper.toEntity(dto);
        Task saved = repository.save(task);
        return mapper.toDto(saved);
    }

    public List<TaskDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
