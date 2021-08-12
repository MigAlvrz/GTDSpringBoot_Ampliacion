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
	
	@Autowired
	private UserService userService;
	
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
		System.out.println("[DONE]");
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
		System.out.println("[DONE]");
	}
	
	
	
}


