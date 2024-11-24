package com.example.scheduler.runner;

import com.example.scheduler.entity.SchedulerEntity;
import com.example.scheduler.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class DynamicScheduler {

    @Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private ConcurrentHashMap<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 60000)
    public void reloadSchedulers() {
        System.out.println("Reloading schedulers");
        schedulerRepository.findAll().forEach(this::scheduleTask);
    }

    private void scheduleTask(SchedulerEntity scheduler) {
        if (!scheduler.isActive()) {
            cancelTask(scheduler.getId());
            return;
        }

        scheduledTasks.computeIfAbsent(scheduler.getId(), id -> taskScheduler.schedule(() -> executeTask(scheduler), new CronTrigger(scheduler.getCronExpression())));
    }

    private void executeTask(SchedulerEntity scheduler) {
        System.out.println("Executing task for scheduler ID: " + scheduler.getId());
        schedulerRepository.updateLastTriggerAndCompletionTime(
                scheduler.getId(), LocalDateTime.now(), LocalDateTime.now()
        );
    }

    private void cancelTask(Long id) {
        ScheduledFuture<?> future = scheduledTasks.remove(id);
        if (future != null) {
            future.cancel(true);
        }
    }
}