package com.daily.repository;

import com.daily.model.Task;
import com.daily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);

}
