package com.capgemini.service;

import java.util.List;

import com.capgemini.model.TaskVO;

public interface TaskService {
	
	public TaskVO add(TaskVO task);
	public TaskVO delete(TaskVO task);
	public TaskVO deleteById(int id);
	public List<TaskVO> listAll();
	public TaskVO findById(int id);

}
