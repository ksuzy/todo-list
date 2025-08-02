package de.ksuzy.todolist.controller;

import de.ksuzy.todolist.dto.TaskDTO;
import de.ksuzy.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/v1/tasks")
    @RequiredArgsConstructor
    @CrossOrigin(origins = "http://localhost:3000") // Разрешаем доступ для React во время разработки
    public class TaskController {

        private final TaskService service;

        @GetMapping
        public ResponseEntity<List<TaskDTO>> findAll() {
            return ResponseEntity.ok(service.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO dto) {
            TaskDTO created = service.create(dto);
            return ResponseEntity.ok(created);
        }

        @PutMapping("/{id}")
        public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO dto) {
            return service.update(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            boolean deleted = service.delete(id);
            return deleted
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        }
}
