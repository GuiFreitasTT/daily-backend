package com.daily.controller;

import com.daily.dto.TaskRequestDTO;
import com.daily.dto.TaskResponseDTO;
import com.daily.model.Task;
import com.daily.model.User;
import com.daily.repository.DailyRepository;
import com.daily.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task")
public class TasksController {

    @Autowired
    private DailyRepository repository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void save(@RequestBody TaskRequestDTO task, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userRepository.findByLogin(userDetails.getUsername());

        Task taskData = new Task(task);

        taskData.setUser(user);
        repository.save(taskData);
    }

    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDTO taskDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userRepository.findByLogin(userDetails.getUsername());

        Optional<Task> optionalTask = repository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task taskData = optionalTask.get();
            taskData.setTitle(taskDTO.title());
            taskData.setDescription(taskDTO.description());
            repository.save(taskData);
        }
    }

    @GetMapping
    public List<TaskResponseDTO> getById(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userRepository.findByLogin(userDetails.getUsername());
        List<Task> tasks = repository.findByUser(user);
        List<TaskResponseDTO> taskList = tasks.stream().map(TaskResponseDTO::new).collect(Collectors.toList());
         return taskList;
     }

    @DeleteMapping("/{taskId}")
    public void delete(@PathVariable Long taskId){
        repository.deleteById(taskId);
    }


}
