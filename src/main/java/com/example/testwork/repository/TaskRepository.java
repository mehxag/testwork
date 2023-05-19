package com.example.testwork.repository;


import com.example.testwork.dto.TaskDTO;
import com.example.testwork.entity.TaskEntity;

import java.util.List;
import java.util.Optional;


public interface TaskRepository {
    void saveTask(TaskEntity taskEntity);

    Optional<List<TaskDTO>> getShortTask();

    Optional<TaskEntity> getTaskById(int id);

    void updateTaskById(int id, TaskEntity taskEntity);

    void assignPerformerToTask(int taskId, int performerId);
}
