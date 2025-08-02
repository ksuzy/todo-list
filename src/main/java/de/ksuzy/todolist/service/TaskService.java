package de.ksuzy.todolist.service;

import de.ksuzy.todolist.database.entity.Task;
import de.ksuzy.todolist.database.repository.TaskRepository;
import de.ksuzy.todolist.dto.TaskDTO;
import de.ksuzy.todolist.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    public List<TaskDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public Optional<TaskDTO> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    public TaskDTO create(TaskDTO dto) {
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<TaskDTO> update(Long id, TaskDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    mapper.updateTaskFromDto(dto, entity);
                    return entity;
                })
                .map(repository::saveAndFlush)
                .map(mapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return true;
                })
                .orElse(false);
    }
}