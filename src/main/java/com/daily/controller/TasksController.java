package com.daily.controller;

import com.daily.dto.TaskRequestDTO;
import com.daily.dto.TaskResponseDTO;
import com.daily.model.Task;
import com.daily.repository.DailyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TasksController {

    @Autowired
    private DailyRepository repository;

    @PostMapping
    public void save(@RequestBody TaskRequestDTO task){
        Task taskData = new Task(task);
        repository.save(taskData);
        return;
    }

    @GetMapping
    public List<TaskResponseDTO> getAll(){
        List<TaskResponseDTO> taskList = repository.findAll().stream().map(TaskResponseDTO::new).toList();
        return taskList;
    }


}
