package de.ksuzy.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private long id;

    private String title;

    private boolean done;

    private LocalDateTime createdAt;

    private LocalDateTime deadline;
}

