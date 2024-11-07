package com.daily.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.task.dto.TaskRequestDTO;
import com.daily.task.dto.TaskResponseDTO;
import com.daily.task.service.TaskService;

@RestController
@RequestMapping("task")
public class TasksController {

	@Autowired
	private TaskService taskService;

	@PostMapping
	public void save(@RequestBody TaskRequestDTO task, Authentication authentication) {
		taskService.save(task, authentication);
	}

	@PutMapping("/{taskId}")
	public void updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDTO taskDTO, Authentication authentication) {
		taskService.updateTask(taskId, taskDTO, authentication);
	}

	@GetMapping
	public List<TaskResponseDTO> getById(Authentication authentication) {
		return taskService.getById(authentication);
	}

	@DeleteMapping("/{taskId}")
	public void delete(@PathVariable Long taskId) {
		taskService.deleteById(taskId);
	}

}
