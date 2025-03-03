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
	public TaskVO delete(TaskVO task) throws IllegalArgumentException {
		if (taskRepo.findById(task.getIdtask()).isPresent()) {
			taskRepo.delete(task);
			return task;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public TaskVO deleteById(int id) throws IllegalArgumentException {
		TaskVO task = taskRepo.findById(id).get();
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
	public TaskVO findById(int id) throws IllegalArgumentException {
		if (taskRepo.findById(id).isPresent()) {
			return taskRepo.findById(id).get();
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<TaskVO> listInboxTask(UserVO user) {
		if (userRepo.findById(user.getIduser()).isPresent()) {
			// primero buscamos que exista el usuario, luego buscamos la categoria
			// sabemos que la categoria INBOX de cada usuario se crea por defecto al
			// crear un usuario nuevo, por lo que su id=1
			CategoryVO catInbox = catRepo.findById(1).get();
			// como hemos de listar las tareas creadas pero no terminadas, la fecha
			// finished es null
			LocalDate finished = null;
			// utilizamos el repo de JPA para buscar la lista de Task con categoria
			// INBOX por cada user
			List<TaskVO> listInbox = taskRepo.findByUserTaskAndCategoryTaskAndFinishedOrderByPlannedAsc(user, catInbox,
					finished);
			return listInbox;
		}

		throw new IllegalArgumentException();
	}

	@Override
	public List<TaskVO> listTodayTask(UserVO user) throws IllegalArgumentException {
		if (userRepo.findById(user.getIduser()).isPresent()) {
			// primero buscamos que exista el usuario
			// como hemos de listar las tareas creadas pero no terminadas, la fecha
			// finished es null
			LocalDate finished = null;
			// como hemos de listar las tareas planeadas para hoy, planned es fecha actual
			LocalDate planned = LocalDate.now();
			// utilizamos el repo de JPA para buscar la lista de Task planeadas para hoy y
			// las atrasadas, ordenadas por categoria de usuario y por fecha planeada

			List<TaskVO> listToday = taskRepo
					.findByUserTaskAndFinishedAndPlannedLessThanEqualOrderByCategoryTaskAscPlannedAsc(user, finished,
							planned);
			return listToday;
		}

		throw new IllegalArgumentException();
	}

	@Override
	public List<TaskVO> listWeekTask(UserVO user) throws IllegalArgumentException {
		if (userRepo.findById(user.getIduser()).isPresent()) {
			// primero buscamos que exista el usuario
			// como hemos de listar las tareas creadas pero no terminadas, la fecha
			// finished es null
			LocalDate finished = null;
			// como hemos de listar las tareas planeadas para la semana, planned es fecha
			// actual más siete días
			LocalDate planned = LocalDate.now().plusDays(7);
			// utilizamos el repo de JPA para buscar la lista de Task planeadas para hoy y
			// las atrasadas, ordenadas por categoria de usuario y por fecha planeada

			List<TaskVO> listWeek = taskRepo
					.findByUserTaskAndFinishedAndPlannedLessThanEqualOrderByPlannedAscCategoryTaskAsc(user, finished,
							planned);
			return listWeek;
		}

		throw new IllegalArgumentException();
	}

	@Override
	public List<TaskVO> listCategoryTask(UserVO user, CategoryVO category) throws IllegalArgumentException {
		if (userRepo.findById(user.getIduser()).isPresent()) {
			// primero buscamos que exista el usuario, luego buscamos que exista la
			// categoria
			if (catRepo.findById(category.getIdcategory()).isPresent()) {
				// como hemos de listar las tareas creadas pero no terminadas, la fecha
				// finished es null
				LocalDate finished = null;
				// utilizamos el repo de JPA para buscar la lista de Task con categoria
				// que introduzcamos por cada user
				List<TaskVO> listCategory = taskRepo.findByUserTaskAndCategoryTaskAndFinishedOrderByPlannedAsc(user, category, finished);
				return listCategory;

			}
			throw new IllegalArgumentException();
		}
		throw new IllegalArgumentException();
	}


}
