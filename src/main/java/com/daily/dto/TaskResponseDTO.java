package com.daily.dto;

import com.daily.model.Task;
import com.daily.model.User;

public record TaskResponseDTO(Long id, String description, User user){

    public TaskResponseDTO(Task task){
        this(task.getId(), task.getDescription(), task.getUser());
    }
}
