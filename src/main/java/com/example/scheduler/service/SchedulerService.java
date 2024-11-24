package com.example.scheduler.service;

import com.example.scheduler.entity.SchedulerEntity;
import com.example.scheduler.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    private SchedulerRepository schedulerRepository;

    public List<SchedulerEntity> getAllSchedulers() {
        return schedulerRepository.findAll();
    }

    public SchedulerEntity createScheduler(SchedulerEntity scheduler) {
        if (!CronExpression.isValidExpression(scheduler.getCronExpression())) {
            throw new IllegalArgumentException("Invalid cron expression");
        }
        return schedulerRepository.save(scheduler);
    }

    public SchedulerEntity updateScheduler(Long id, SchedulerEntity updatedScheduler) {
        return schedulerRepository.findById(id).map(scheduler -> {
            scheduler.setCronExpression(updatedScheduler.getCronExpression());
            scheduler.setActive(updatedScheduler.isActive());
            return schedulerRepository.save(scheduler);
        }).orElseThrow(() -> new IllegalArgumentException("Scheduler not found with id: " + id));
    }

    public void deleteScheduler(Long id) {
        schedulerRepository.deleteById(id);
    }
}

