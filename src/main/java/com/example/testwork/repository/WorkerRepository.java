package com.example.testwork.repository;

import com.example.testwork.entity.WorkerEntity;

import java.util.List;

public interface WorkerRepository {

    void createWorker(WorkerEntity workerEntity);

    List<WorkerEntity> getAllWorker();

    List<WorkerEntity> getWorkerById(int id);

    void updateWorkerById(int id, WorkerEntity workerEntity);

    void deleteWorkerById(int id);
}
