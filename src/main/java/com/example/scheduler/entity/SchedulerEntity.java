package com.example.scheduler.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduler_config")
public class SchedulerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cron_expression", nullable = false)
    private String cronExpression;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "last_triggered_time")
    private LocalDateTime lastTriggeredTime;

    @Column(name = "last_completion_time")
    private LocalDateTime lastCompletionTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCronExpression() { return cronExpression; }
    public void setCronExpression(String cronExpression) { this.cronExpression = cronExpression; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public LocalDateTime getLastTriggeredTime() { return lastTriggeredTime; }
    public void setLastTriggeredTime(LocalDateTime lastTriggeredTime) { this.lastTriggeredTime = lastTriggeredTime; }

    public LocalDateTime getLastCompletionTime() { return lastCompletionTime; }
    public void setLastCompletionTime(LocalDateTime lastCompletionTime) { this.lastCompletionTime = lastCompletionTime; }
}