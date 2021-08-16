package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserVO;
import com.capgemini.service.CategoryService;
import com.capgemini.service.TaskService;
import com.capgemini.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService catService;

	/**
	 * Creates a new TaskVO, then adds it to the DB
	 * @param body
	 * @return
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody String body) {
		TaskVO newTask = null;
		try {
			newTask = new ObjectMapper().readValue(body, TaskVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		taskService.add(newTask);
		return checkNull(newTask);
	}
	
	/**
	 * Deletes a TaskVo from the DB
	 * @param body
	 * @return
	 */

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody String body) {
		TaskVO exTask = null;
		try {
			exTask = new ObjectMapper().readValue(body, TaskVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		taskService.delete(exTask);
		return checkNull(exTask);
	}

	/**
	 * Finds a TaskVo by it's Id, then deletes it from the database
	 * @param taskId
	 * @return
	 */
	
	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<?> deleteById(@PathVariable int taskId) {
		TaskVO task = taskService.deleteById(taskId);
		return checkNull(task);
	}
	
	/**
	 * Returns all the existing TaskVos from the DB
	 * @return
	 */

	@GetMapping("/listAll")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(taskService.listAll(), HttpStatus.OK);
	}

	/**
	 * Finds a TaskVO by it's ID
	 * @param TaskId
	 * @return
	 */
	
	@GetMapping("/find/{TaskId}")
	public ResponseEntity<?> findById(@PathVariable int TaskId) {
		TaskVO task = taskService.findById(TaskId);
		return checkNull(task);
	}

	/**
	 * Returns all the existing TaskVOs from an UserVO
	 * @param userId
	 * @return
	 */
	
	@GetMapping("/listInbox/{userId}")
	public ResponseEntity<?> findInbox(@PathVariable int userId) {
		if (userService.findById(userId) != null) {
			UserVO user = userService.findById(userId);
			return new ResponseEntity<>(taskService.listInboxTask(user), HttpStatus.OK);
		}
		return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Returns all the TaskVOs planned for today
	 * @param userId
	 * @return
	 */
	
	@GetMapping("/listToday/{userId}")
	public ResponseEntity<?> findToday(@PathVariable int userId) {
		if (userService.findById(userId) != null) {
			UserVO user = userService.findById(userId);
			return new ResponseEntity<>(taskService.listTodayTask(user), HttpStatus.OK);
		}
		return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Returns all the TaskVOs planned for this week
	 * @param userId
	 * @return
	 */
	
	@GetMapping("/listWeek/{userId}")
	public ResponseEntity<?> findWeek(@PathVariable int userId) {
		if (userService.findById(userId) != null) {
			UserVO user = userService.findById(userId);
			return new ResponseEntity<>(taskService.listWeekTask(user), HttpStatus.OK);
		}
		return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Returns all the TaskVOs from an specific CategoryVO
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	
	@GetMapping("/listCategory/{userId}/{categoryId}")
	public ResponseEntity<?> findCategory(@PathVariable int userId, @PathVariable int categoryId) {
		if (userService.findById(userId) != null && catService.findById(categoryId)!=null) {
			UserVO user = userService.findById(userId);
			CategoryVO cat = catService.findById(categoryId);
			return new ResponseEntity<>(taskService.listCategoryTask(user, cat), HttpStatus.OK);
		}
		return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Checks if a TaskVO is correct, then returns an Ok httpStatus
	 * otherwise, returns a Bad_Request
	 * @param cat
	 * @return
	 */
	
	private ResponseEntity<?> checkNull(TaskVO task) {
		if (task != null)
			return new ResponseEntity<>(task, HttpStatus.OK);
		return new ResponseEntity<>(task, HttpStatus.BAD_REQUEST);
	}
}
