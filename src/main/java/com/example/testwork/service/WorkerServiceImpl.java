package com.example.testwork.service;

import com.example.testwork.entity.WorkerEntity;
import com.example.testwork.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: перехватывать исключения везде

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public void createWorker(WorkerEntity workerEntity) {
        workerRepository.createWorker(workerEntity);
    }

    @Override
    public List<WorkerEntity> getAllWorker() {
        return workerRepository.getAllWorker();
    }

    @Override
    public List<WorkerEntity> getWorkerById(int id) {
        return workerRepository.getWorkerById(id);
    }

    @Override
    public void updateWorkerById(int id, WorkerEntity workerEntity) {
        workerRepository.updateWorkerById(id, workerEntity);
    }

    @Override
    public void deleteWorkerById(int id) {
        workerRepository.deleteWorkerById(id);
    }
}
