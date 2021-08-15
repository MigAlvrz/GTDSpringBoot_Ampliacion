package com.capgemini.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserVO;
import com.capgemini.repository.CategoryRepository;
import com.capgemini.repository.TaskRepository;
import com.capgemini.repository.UserRepository;
import com.capgemini.service.TaskService;

@Service
public class TaskServiceImplementation implements TaskService {
	
	@Autowired
	private TaskRepository taskRepo;	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	

	@Override
	public TaskVO add(TaskVO task) {
		return taskRepo.save(task);
	}
	
	@Override
	public TaskVO modify(TaskVO task) {		
		return taskRepo.save(task);
	}

	@Override
	public TaskVO delete(TaskVO task) throws IllegalArgumentException{
		if (taskRepo.findById(task.getIdtask()).isPresent()) {
			taskRepo.delete(task);
			return task;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public TaskVO deleteById(int id) throws IllegalArgumentException{
		TaskVO task=taskRepo.findById(id).get();		
		if (taskRepo.findById(id).isPresent()) {
			taskRepo.deleteById(id);
			return task;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<TaskVO> listAll() {		
		return taskRepo.findAll();
	}

	@Override
	public TaskVO findById(int id) throws IllegalArgumentException{
		if (taskRepo.findById(id).isPresent()) {
			return taskRepo.findById(id).get();
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<TaskVO> listInboxTask(UserVO user) {
		if(userRepo.findById(user.getIduser()).isPresent()) {
			//primero buscamos que exista el usuario, luego buscamos la categoria
			//sabemos que la categoria INBOX de cada usuario se crea por defecto al
			//crear un usuario nuevo, por lo que su id=1
			CategoryVO catInbox=catRepo.findById(1).get();
			//como hemos de listar las tareas creadas pero no terminadas, la fecha
			//finished es null
			LocalDate finished = null;
			//utilizamos el repo de JPA para buscar la lista de Task con categoria
			//INBOX por cada user
			List<TaskVO> listInbox = taskRepo.findByUserTaskAndCategoryTaskAndFinishedOrderByPlannedAsc(user,catInbox,finished);
			return listInbox;
		}

		throw new IllegalArgumentException();
	}



}
