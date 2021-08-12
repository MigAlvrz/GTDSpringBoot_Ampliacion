package com.capgemini.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.TaskVO;
import com.capgemini.repository.TaskRepository;
import com.capgemini.service.TaskService;

@Service
public class TaskServiceImplementation implements TaskService {
	
	@Autowired
	private TaskRepository taskRepo;

	@Override
	public TaskVO add(TaskVO task) {
		if (taskRepo.findById(task.getIdtask()).isEmpty()) {
			return taskRepo.save(task);
		}
		return null;
	}
	
	@Override
	public TaskVO modify(TaskVO task) {		
		return taskRepo.save(task);
	}

	@Override
	public TaskVO delete(TaskVO task) {
		if (taskRepo.findById(task.getIdtask()).isPresent()) {
			taskRepo.delete(task);
			return task;
		}
		return null;
	}

	@Override
	public TaskVO deleteById(int id) {
		TaskVO task=taskRepo.findById(id).get();		
		if (taskRepo.findById(id).isPresent()) {
			taskRepo.deleteById(id);
			return task;
		}
		return null;
	}

	@Override
	public List<TaskVO> listAll() {		
		return taskRepo.findAll();
	}

	@Override
	public TaskVO findById(int id) {
		if (taskRepo.findById(id).isPresent()) {
			return taskRepo.findById(id).get();
		}
		return null;
	}



}
