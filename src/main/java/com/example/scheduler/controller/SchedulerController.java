package com.example.scheduler.controller;

import com.example.scheduler.entity.SchedulerEntity;
import com.example.scheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedulers")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping
    public List<SchedulerEntity> getAllSchedulers() {
        return schedulerService.getAllSchedulers();
    }

    @PostMapping
    public SchedulerEntity createScheduler(@RequestBody SchedulerEntity scheduler) {
        return schedulerService.createScheduler(scheduler);
    }

    @PutMapping("/{id}")
    public SchedulerEntity updateScheduler(@PathVariable Long id, @RequestBody SchedulerEntity updatedScheduler) {
        return schedulerService.updateScheduler(id, updatedScheduler);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScheduler(@PathVariable Long id) {
        schedulerService.deleteScheduler(id);
        return ResponseEntity.noContent().build();
    }
}