package de.ksuzy.todolist.unit.mapper;

import de.ksuzy.todolist.database.entity.Task;
import de.ksuzy.todolist.dto.TaskDTO;
import de.ksuzy.todolist.mapper.TaskMapper;
import de.ksuzy.todolist.mapper.TaskMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskMapperTest {

    private TaskMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new TaskMapperImpl(); // Сгенерированная реализация MapStruct
    }

    @Test
    void testToDto() {
        Task task = Task.builder()
                .id(1L)
                .title("Test")
                .done(true)
                .createdAt(LocalDateTime.now())
                .deadline(LocalDateTime.now().plusDays(1))
                .build();

        TaskDTO dto = mapper.toDto(task);

        assertEquals("Test", dto.getTitle());
        assertTrue(dto.isDone());
    }
}
