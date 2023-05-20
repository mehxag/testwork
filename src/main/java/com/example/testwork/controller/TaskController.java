package com.example.testwork.controller;


import com.example.testwork.dto.TaskDTO;
import com.example.testwork.entity.TaskEntity;
import com.example.testwork.exception.TaskException;
import com.example.testwork.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create/queue")
    public void createTask(@RequestBody TaskEntity taskEntity) {
        taskService.addTask(taskEntity);
    }

    @PostMapping("/create/database")
    public void createTaskDatabase() throws TaskException {
        taskService.createTask();
    }

    @GetMapping("/short/tasks")
    public List<TaskDTO> getShortTask() throws TaskException {
        return taskService.getShortTask();
    }

    @GetMapping("/{id}")
    public TaskEntity getTaskById(@PathVariable() int id) throws TaskException {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public void updateTaskById(@PathVariable int id, @RequestBody TaskEntity taskEntity) {
        taskService.updateTaskById(id, taskEntity);
    }

    @PutMapping("/{taskId}/performer/{performerId}")
    public void assignPerformerToTask(@PathVariable int taskId, @PathVariable int performerId) {
        taskService.assignPerformerToTask(taskId, performerId);
    }
}
