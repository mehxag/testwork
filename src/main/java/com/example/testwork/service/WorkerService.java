package com.example.testwork.service;

import com.example.testwork.entity.WorkerEntity;

import java.util.List;

public interface WorkerService {

    void createWorker(WorkerEntity workerEntity);

    List<WorkerEntity> getAllWorker();

    List<WorkerEntity> getWorkerById(int id);

    void updateWorkerById(int id, WorkerEntity workerEntity);

    void deleteWorkerById(int id);
}
