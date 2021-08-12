package com.capgemini;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.GroupUserVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserStatus;
import com.capgemini.model.UserVO;
import com.capgemini.service.TaskService;
import com.capgemini.service.UserService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para TaskVO")
class TaskTest {

	@Autowired
	private TaskService taskService;
	
	@Test
	@Order(1)
	@DisplayName("Insertar tarea")
	void testInsertTask() {
		System.out.println();
		System.out.println("[TEST 1]");
		


		taskService.add(new TaskVO("tarea1", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null, null, null));
		taskService.add(new TaskVO("tarea2", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null, null, null));
		taskService.add(new TaskVO("tarea3", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null, null, null));
		taskService.add(new TaskVO("tarea4", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null, null, null));
		taskService.add(new TaskVO("tarea5", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null, null, null));

		assertEquals(5, taskService.listAll().size());
		System.out.println("[DONE]\n");
	}

	@Test
	@Order(2)
	@DisplayName("Borrar tasks")
	void testDeleteTask() {
		System.out.println();
		System.out.println("[TEST 2]");
		
		TaskVO task = taskService.findById(1);
		taskService.delete(task);

		
		assertEquals(4, taskService.listAll().size());
		System.out.println("[DONE]\n");
	}
	
	@Test
	@Order(3)
	@DisplayName("Borrar Tasks con id")
	void testeDeleteTaskById() {
		System.out.println();
		System.out.println("[TEST 3]");
		
		taskService.deleteById(2);

		assertEquals(3, taskService.listAll().size());
		System.out.println("[DONE]\n");
	}
	
	@Test
	@Order(4)
	@DisplayName("Buscar todas las task")
	void testListAllTasks() {
		System.out.println();
		System.out.println("[TEST 4]");
		System.out.println("Current tasks in database: ");
		List<TaskVO> tasks = taskService.listAll();
		for (TaskVO t : tasks) {
			System.out.println(t.getTitle());
		}
		
		assertEquals(3, taskService.listAll().size());
		System.out.println("[DONE]\n");
	}
	
	@Test
	@Order(5)
	@DisplayName("Buscar Task por id")
	void testFindTaskById() {
		System.out.println();
		System.out.println("[TEST 5]");
		
		System.out.println("Title with ID=3 --> "+taskService.findById(3).getTitle());
		
		assertEquals("tarea3", taskService.findById(3).getTitle());
		System.out.println("[DONE]\n");
	}
	
	@Test
	@Order(6)
	@DisplayName("Modificar Task")
	void testModifyTask() {
		System.out.println();
		System.out.println("[TEST 6]");
		
		TaskVO task = taskService.findById(4);
		LocalDate newPlanned = task.getPlanned().plusDays(1);
		System.out.println("Task with id=4 is "+ task.getTitle()+"\n and was scheduled for "+task.getPlanned());
		task.setTitle("newTitle4");
		task.setPlanned(newPlanned);
		taskService.modify(task);
		System.out.println("New title: "+task.getTitle()+"\nNew planned finished time: "+task.getPlanned());
		
		assertEquals("newTitle4", taskService.findById(4).getTitle());
		assertEquals(newPlanned, taskService.findById(4).getPlanned());
		
		System.out.println("[DONE]\n");
	}
	
	
}


