package com.example.testwork.service;


import com.example.testwork.dto.TaskDTO;
import com.example.testwork.entity.TaskEntity;
import com.example.testwork.exception.TaskException;
import com.example.testwork.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static com.example.testwork.exception.TaskException.TaskExceptionType.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final int FIXED_THREAD_POOL = 3;

    private TaskRepository taskRepository;

    private BlockingQueue<TaskEntity> queue = new LinkedBlockingQueue<>();

    private ExecutorService executor = Executors.newFixedThreadPool(FIXED_THREAD_POOL);

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(TaskEntity taskEntity) {
        queue.offer(taskEntity);
    }

    @Override
    public void createTask() throws TaskException {
        for (int i = 0; i < FIXED_THREAD_POOL; i++) {
            executor.submit(() -> {
                try {
                    TaskEntity taskQueue = queue.take();
                    taskRepository.saveTask(taskQueue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public List<TaskDTO> getShortTask() throws TaskException {
        return taskRepository.getShortTask().orElseThrow(() -> new TaskException(TASK_FAILED_DATA.getMessage()));
    }

    @Override
    public TaskEntity getTaskById(int id) throws TaskException {
        return taskRepository.getTaskById(id).orElseThrow(() -> new TaskException(TASK_FAILED_GET_BY_ID.getMessage()));
    }

    @Override
    public void updateTaskById(int id, TaskEntity taskEntity) {
        taskRepository.updateTaskById(id, taskEntity);
    }

    @Override
    public void assignPerformerToTask(int taskId, int performerId) {
        taskRepository.assignPerformerToTask(taskId, performerId);
    }
}
