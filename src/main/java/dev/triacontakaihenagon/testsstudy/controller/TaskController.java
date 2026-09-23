package dev.triacontakaihenagon.testsstudy.controller;

import dev.triacontakaihenagon.testsstudy.dto.TaskRequest;
import dev.triacontakaihenagon.testsstudy.dto.TaskResponse;
import dev.triacontakaihenagon.testsstudy.mapper.TaskMapper;
import dev.triacontakaihenagon.testsstudy.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
        return taskMapper.toResponseList(taskService.getAllTask());
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id, String auth) {
        return taskMapper.toResponse(taskService.getTaskById(id, auth, false));
    }

    @PostMapping
    public TaskResponse postTasks(@RequestBody @Valid TaskRequest request, String auth) {
        return taskMapper.toResponse(taskService.createTask(request, auth));
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest request, String auth) {
        return taskMapper.toResponse(taskService.updateTask(id, request, auth, false));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, String auth) {
        taskService.deleteTask(id, auth, false);
    }
}
