package com.daily.controller;

import com.daily.dto.TaskRequestDTO;
import com.daily.dto.TaskResponseDTO;
import com.daily.model.Task;
import com.daily.repository.DailyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDTO taskDTO) {
        Optional<Task> optionalTask = repository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task taskData = optionalTask.get();
            taskData.setTitle(taskDTO.title());
            taskData.setDescription(taskDTO.description());
            repository.save(taskData);
        }
    }

    @GetMapping
    public List<TaskResponseDTO> getAll(){
        List<TaskResponseDTO> taskList = repository.findAll().stream().map(TaskResponseDTO::new).toList();
        return taskList;
    }

    @DeleteMapping("/{taskId}")
    public void delete(@PathVariable Long taskId){
        repository.deleteById(taskId);
    }


}
