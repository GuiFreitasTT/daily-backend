package com.daily.task.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.daily.task.dto.TaskRequestDTO;
import com.daily.task.dto.TaskResponseDTO;
import com.daily.task.model.Task;
import com.daily.task.repository.TaskRepository;
import com.daily.user.model.User;
import com.daily.user.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;

	@Autowired
	private UserRepository userRepository;

	public void save(TaskRequestDTO task, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = (User) userRepository.findByLogin(userDetails.getUsername());

		Task taskData = new Task(task);

		taskData.setUser(user);
		repository.save(taskData);
	}

	public void updateTask(Long taskId, TaskRequestDTO taskDTO, Authentication authentication) {
		Optional<Task> optionalTask = repository.findById(taskId);

		if (optionalTask.isPresent()) {
			Task taskData = optionalTask.get();
			taskData.setDescription(taskDTO.description());
			repository.save(taskData);
		}
	}

	public List<TaskResponseDTO> getById(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = (User) userRepository.findByLogin(userDetails.getUsername());
		List<Task> tasks = repository.findByUser(user);
		List<TaskResponseDTO> taskList = tasks.stream().map(TaskResponseDTO::new).collect(Collectors.toList());
		return taskList;
	}

	public void deleteById(Long taskId) {
		repository.deleteById(taskId);
	}

}
