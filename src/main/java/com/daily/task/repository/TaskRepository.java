package com.daily.task.repository;

import com.daily.task.model.Task;
import com.daily.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);

}
