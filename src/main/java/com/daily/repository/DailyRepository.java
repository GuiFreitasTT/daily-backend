package com.daily.repository;

import com.daily.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRepository extends JpaRepository<Task, Long> {
}
