package com.example.scheduler.repository;

import com.example.scheduler.entity.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE com.example.scheduler.entity.SchedulerEntity s SET s.lastTriggeredTime = :triggeredTime, s.lastCompletionTime = :completionTime WHERE s.id = :id")
    void updateLastTriggerAndCompletionTime(Long id, LocalDateTime triggeredTime, LocalDateTime completionTime);
}
