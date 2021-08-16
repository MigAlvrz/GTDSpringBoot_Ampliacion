package com.capgemini.service;

import java.util.List;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserVO;

public interface TaskService {
	
	public TaskVO add(TaskVO task);
	public TaskVO modify(TaskVO task);
	public TaskVO delete(TaskVO task) throws IllegalArgumentException;
	public TaskVO deleteById(int id) throws IllegalArgumentException;
	public List<TaskVO> listAll();
	public TaskVO findById(int id) throws IllegalArgumentException;
	public List<TaskVO> listInboxTask(UserVO user) throws IllegalArgumentException;
	public List<TaskVO> listTodayTask(UserVO user) throws IllegalArgumentException;
	public List<TaskVO> listWeekTask(UserVO user) throws IllegalArgumentException;
	public List<TaskVO> listCategoryTask(UserVO user, CategoryVO category) throws IllegalArgumentException;

}
