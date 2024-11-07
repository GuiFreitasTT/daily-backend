package com.daily.task.dto;

import com.daily.task.model.Task;
import com.daily.user.model.User;

public record TaskResponseDTO(Long id, String description, User user){

    public TaskResponseDTO(Task task){
        this(task.getId(), task.getDescription(), task.getUser());
    }
}
