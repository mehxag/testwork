package com.example.testwork.service;

import com.example.testwork.dto.TaskDTO;
import com.example.testwork.entity.TaskEntity;
import com.example.testwork.exception.TaskException;

import java.util.List;

public interface TaskService {

    void addTask(TaskEntity taskEntity);

    void createTask() throws TaskException;

    List<TaskDTO> getShortTask() throws TaskException;

    TaskEntity getTaskById(int id) throws TaskException;

    void updateTaskById(int id, TaskEntity taskEntity);

    void assignPerformerToTask(int taskId, int performerId);
}
