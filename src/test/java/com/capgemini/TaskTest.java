package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.GroupUserVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserStatus;
import com.capgemini.model.UserVO;
import com.capgemini.service.CategoryService;
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
	@Autowired
	private CategoryService catService;
	
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
	
	@Test
	@Order(7)
	@DisplayName("Listar task con categoria inbox sin terminar")
	void testListInboxTask() {
		System.out.println();
		System.out.println("[TEST 7]");
		
		//para poder testear, debemos crear un user,unas categorias para ese user
		UserVO user1 = new UserVO("user1","user1@capgemini.com","user1",UserStatus.ENABLED,false,new ArrayList<TaskVO>(),new ArrayList<CategoryVO>(),new ArrayList<GroupUserVO>());
		userService.add(user1);		
		//categorias para user1
		CategoryVO cat1user1 = new CategoryVO("INBOX",new ArrayList<TaskVO>(),userService.findById(1));
		CategoryVO cat2user1 = new CategoryVO("Categoria 2",new ArrayList<TaskVO>(),userService.findById(1));
		catService.add(cat1user1);
		catService.add(cat2user1);
		//creamos unas tareas para user1, el grupo de la tarea lo metemos como null para no sobrecargar el test
		//creamos 7 tareas pero solo 3 en inbox sin finalizar
		TaskVO task1user1 = new TaskVO("Tarea 1","lorem ipsum dolor sit amet",LocalDate.now(),null,null,userService.findById(1),catService.findById(1),null);//INBOX sin acabar
		TaskVO task2user1 = new TaskVO("Tarea 2","lorem ipsum dolor sit amet",LocalDate.now(),null,null,userService.findById(1),catService.findById(1),null);//INBOX sin acabar
		TaskVO task3user1 = new TaskVO("Tarea 3","lorem ipsum dolor sit amet",LocalDate.now(),null,null,userService.findById(1),catService.findById(1),null);//INBOX sin acabar
		TaskVO task4user1 = new TaskVO("Tarea 4","lorem ipsum dolor sit amet",LocalDate.now(),null,LocalDate.now(),userService.findById(1),catService.findById(1),null);//INBOX acabada
		TaskVO task5user1 = new TaskVO("Tarea 5","lorem ipsum dolor sit amet",LocalDate.now(),null,LocalDate.now(),userService.findById(1),catService.findById(1),null);//INBOX acabada
		TaskVO task6user1 = new TaskVO("Tarea 6","lorem ipsum dolor sit amet",LocalDate.now(),null,null,userService.findById(1),catService.findById(2),null);//Categoria 2 sin acabar
		TaskVO task7user1 = new TaskVO("Tarea 7","lorem ipsum dolor sit amet",LocalDate.now(),null,null,userService.findById(1),catService.findById(2),null);//Categoria 2 sin acabar
		taskService.add(task1user1);
		taskService.add(task2user1);
		taskService.add(task3user1);
		taskService.add(task4user1);
		taskService.add(task5user1);
		taskService.add(task6user1);
		taskService.add(task7user1);
		
		List<TaskVO> listTaskInboxUser1 = taskService.listInboxTask(user1);
		for (TaskVO t : listTaskInboxUser1) {
			System.out.println("Soy la "+t.getTitle()+" de la categoria "+t.getCategoryTask().getName()+" del "+t.getUserTask().getLogin());
		}
		
		//deberíamos encontrar 3 tareas en INBOX sin finalizar para el user1
		assertEquals(3, taskService.listInboxTask(user1).size());

		
		System.out.println("[DONE]\n");
	}
	
	
	
}


