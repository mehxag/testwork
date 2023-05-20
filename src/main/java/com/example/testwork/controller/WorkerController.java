package com.example.testwork.controller;

import com.example.testwork.entity.WorkerEntity;
import com.example.testwork.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("/creator")
    public ResponseEntity<WorkerEntity> createWorker(@RequestBody WorkerEntity workerEntity) {
        workerService.createWorker(workerEntity);
        return ResponseEntity.ok(workerEntity);
    }

    @GetMapping("/all")
    public List<WorkerEntity> getAllWorker() {
        return workerService.getAllWorker();
    }

    @GetMapping("/{id}")
    public List<WorkerEntity> getWorkerById(@PathVariable int id) {
        return workerService.getWorkerById(id);
    }

    @PutMapping("/update/{id}")
    public void updateWorkerById(@PathVariable int id, @RequestBody WorkerEntity workerEntity) {
        workerService.updateWorkerById(id, workerEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWorkerById(@PathVariable int id) {
        workerService.deleteWorkerById(id);
    }
}
