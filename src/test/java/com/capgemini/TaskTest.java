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

		taskService.add(new TaskVO("tarea1", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null,
				null, null));
		taskService.add(new TaskVO("tarea2", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null,
				null, null));
		taskService.add(new TaskVO("tarea3", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null,
				null, null));
		taskService.add(new TaskVO("tarea4", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null,
				null, null));
		taskService.add(new TaskVO("tarea5", "comentario", LocalDate.now(), LocalDate.now().plusDays(1), null, null,
				null, null));

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

		System.out.println("Title with ID=3 --> " + taskService.findById(3).getTitle());

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
		System.out.println("Task with id=4 is " + task.getTitle() + "\n and was scheduled for " + task.getPlanned());
		task.setTitle("newTitle4");
		task.setPlanned(newPlanned);
		taskService.modify(task);
		System.out.println("New title: " + task.getTitle() + "\nNew planned finished time: " + task.getPlanned());

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

		// para poder testear, debemos crear un user,unas categorias para ese user
		UserVO user1 = new UserVO("user1", "user1@capgemini.com", "user1", UserStatus.ENABLED, false,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>());
		userService.add(user1);
		// categorias para user1
		CategoryVO cat1user1 = new CategoryVO("INBOX", new ArrayList<TaskVO>(), userService.findById(1));
		CategoryVO cat2user1 = new CategoryVO("Categoria 2", new ArrayList<TaskVO>(), userService.findById(1));
		catService.add(cat1user1);
		catService.add(cat2user1);
		// creamos unas tareas para user1, el grupo de la tarea lo metemos como null
		// para no sobrecargar el test
		// creamos 7 tareas pero solo 3 en inbox sin finalizar
		TaskVO task1user1 = new TaskVO("Tarea 1", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 14), null, userService.findById(1), catService.findById(1), null);// INBOX sin acabar
		TaskVO task2user1 = new TaskVO("Tarea 2", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 15), null, userService.findById(1), catService.findById(1), null);// INBOX sin acabar
		TaskVO task3user1 = new TaskVO("Tarea 3", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 13), null, userService.findById(1), catService.findById(1), null);// INBOX sin acabar
		TaskVO task4user1 = new TaskVO("Tarea 4", "lorem ipsum dolor sit amet", LocalDate.now(), null, LocalDate.now(),
				userService.findById(1), catService.findById(1), null);// INBOX acabada
		TaskVO task5user1 = new TaskVO("Tarea 5", "lorem ipsum dolor sit amet", LocalDate.now(), null, LocalDate.now(),
				userService.findById(1), catService.findById(1), null);// INBOX acabada
		TaskVO task6user1 = new TaskVO("Tarea 6", "lorem ipsum dolor sit amet", LocalDate.now(), null, null,
				userService.findById(1), catService.findById(2), null);// Categoria 2 sin acabar
		TaskVO task7user1 = new TaskVO("Tarea 7", "lorem ipsum dolor sit amet", LocalDate.now(), null, null,
				userService.findById(1), catService.findById(2), null);// Categoria 2 sin acabar
		taskService.add(task1user1);
		taskService.add(task2user1);
		taskService.add(task3user1);
		taskService.add(task4user1);
		taskService.add(task5user1);
		taskService.add(task6user1);
		taskService.add(task7user1);

		List<TaskVO> listTaskInboxUser1 = taskService.listInboxTask(user1);
		for (TaskVO t : listTaskInboxUser1) {
			System.out.println("Soy la " + t.getTitle() + " de la categoria " + t.getCategoryTask().getName() + " del "
					+ t.getUserTask().getLogin());
		}

		// deberíamos encontrar 3 tareas en INBOX sin finalizar para el user1
		assertEquals(3, taskService.listInboxTask(user1).size());

		System.out.println("[DONE]\n");
	}

	@Test
	@Order(8)
	@DisplayName("Listar task planedas para hoy (y atrasadas) sin terminar")
	void testListTodayTask() {
		System.out.println();
		System.out.println("[TEST 8]");
		// vamos a trabajar con el user1
		// creamos alguna que otra categoria para que nos lo agrupe por categoria
		CategoryVO cat3user1 = new CategoryVO("Categoria 3", new ArrayList<TaskVO>(), userService.findById(1));
		CategoryVO cat4user1 = new CategoryVO("Categoria 4", new ArrayList<TaskVO>(), userService.findById(1));
		CategoryVO cat5user1 = new CategoryVO("Categoria 5", new ArrayList<TaskVO>(), userService.findById(1));
		catService.add(cat3user1);
		catService.add(cat4user1);
		catService.add(cat5user1);
		// creamos unas tareas para user1, el grupo de la tarea lo metemos como null
		// para no sobrecargar el test
		// ---Tareas para categoria 2 (4 creadas, solo listara 3 por que una es para fecha posterior a localdate.now)
		TaskVO task8user1 = new TaskVO("Tarea 8", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 15), null, userService.findById(1), catService.findById(2), null);
		TaskVO task9user1 = new TaskVO("Tarea 9", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 14), null, userService.findById(1), catService.findById(2), null);
		TaskVO task10user1 = new TaskVO("Tarea 10", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 12), null, userService.findById(1), catService.findById(2), null);
		TaskVO task11user1 = new TaskVO("Tarea 11", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(2), null);
		
		// ---Tareas para categoria 3 (4 creadas, solo listara 3 por que una es para fecha posterior a localdate.now)
		TaskVO task12user1 = new TaskVO("Tarea 12", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 15), null, userService.findById(1), catService.findById(3), null);
		TaskVO task13user1 = new TaskVO("Tarea 13", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 14), null, userService.findById(1), catService.findById(3), null);
		TaskVO task14user1 = new TaskVO("Tarea 14", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 12), null, userService.findById(1), catService.findById(3), null);
		TaskVO task15user1 = new TaskVO("Tarea 15", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(3), null);
		
		// ---Tareas para categoria 4 (4 creadas, solo listara 3 por que una es para fecha posterior a localdate.now)
		TaskVO task16user1 = new TaskVO("Tarea 16", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 15), null, userService.findById(1), catService.findById(4), null);
		TaskVO task17user1 = new TaskVO("Tarea 17", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 14), null, userService.findById(1), catService.findById(4), null);
		TaskVO task18user1 = new TaskVO("Tarea 18", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 12), null, userService.findById(1), catService.findById(4), null);
		TaskVO task19user1 = new TaskVO("Tarea 19", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(4), null);
		
		// ---Tareas para categoria 5 (4 creadas, solo listara 3 por que una es para fecha posterior a localdate.now)
		TaskVO task20user1 = new TaskVO("Tarea 20", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 15), null, userService.findById(1), catService.findById(5), null);
		TaskVO task21user1 = new TaskVO("Tarea 21", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 14), null, userService.findById(1), catService.findById(5), null);
		TaskVO task22user1 = new TaskVO("Tarea 22", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 12), null, userService.findById(1), catService.findById(5), null);
		TaskVO task23user1 = new TaskVO("Tarea 23", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(5), null);
		
		taskService.add(task8user1); 
		taskService.add(task9user1);
		taskService.add(task10user1);
		taskService.add(task11user1);
		taskService.add(task12user1);
		taskService.add(task13user1);
		taskService.add(task14user1);
		taskService.add(task15user1);
		taskService.add(task16user1);
		taskService.add(task17user1);
		taskService.add(task18user1);
		taskService.add(task19user1);
		taskService.add(task20user1);
		taskService.add(task21user1);
		taskService.add(task22user1);
		taskService.add(task23user1);
		
		UserVO user1 = userService.findById(1);
		
		List<TaskVO> listTaskTodayUser1 = taskService.listTodayTask(user1);
		for (TaskVO t : listTaskTodayUser1) {
			System.out.println(t.getCategoryTask().getName()+" - "+t.getTitle()+" - Prevista para: "+ t.getPlanned());
		}
		
		System.out.println("El usuario 1 tiene "+userService.findById(1).getTasks().size()+" tareas pero solo tiene "+listTaskTodayUser1.size()+" previstas para hoy o retrasadas.");
		
		//tenemos 15, por que arrastramos 3 tareas creadas del metodo anterior fechadas para hoy o anterior
		assertEquals(15, listTaskTodayUser1.size());

		System.out.println("[DONE]\n");
	}
	
	@Test
	@Order(9)
	@DisplayName("Listar task planedas para esta semana (y atrasadas) sin terminar")
	void testListWeekTask() {
		System.out.println();
		System.out.println("[TEST 9]");
		
		//en el metodo anterior ya habiamos añadido 4 tareas planeadas para esta proxima semana
		//añadimos alguna más para comprobar que nos agrupa como debe
		
		TaskVO task24user1 = new TaskVO("Tarea 24", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(5), null);
		TaskVO task25user1 = new TaskVO("Tarea 25", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(4), null);
		TaskVO task26user1 = new TaskVO("Tarea 26", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(3), null);
		TaskVO task27user1 = new TaskVO("Tarea 27", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 8, 21), null, userService.findById(1), catService.findById(2), null);
		
		//añadimos 3 con fecha dentro de un mes para hacer doble comprobacion
		TaskVO task28user1 = new TaskVO("Tarea 28", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 9, 21), null, userService.findById(1), catService.findById(2), null);
		TaskVO task29user1 = new TaskVO("Tarea 29", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 9, 23), null, userService.findById(1), catService.findById(2), null);
		TaskVO task30user1 = new TaskVO("Tarea 30", "lorem ipsum dolor sit amet", LocalDate.now(),
				LocalDate.of(2021, 9, 25), null, userService.findById(1), catService.findById(2), null);
		
		taskService.add(task24user1);
		taskService.add(task25user1);
		taskService.add(task26user1);
		taskService.add(task27user1);
		taskService.add(task28user1);
		taskService.add(task29user1);
		taskService.add(task30user1);
		
		UserVO user1 = userService.findById(1);
		List<TaskVO> listTaskWeekUser1 = taskService.listWeekTask(user1);
		for (TaskVO t : listTaskWeekUser1) {
			System.out.println("Día límite planned: "+t.getPlanned()+" - "+t.getTitle()+" - Categoría: "+t.getCategoryTask().getName());
		}
		
		System.out.println("El usuario 1 tiene "+userService.findById(1).getTasks().size()+" tareas pero solo tiene "+listTaskWeekUser1.size()+" previstas para estama semana o retrasadas.");
		
		assertEquals(23, listTaskWeekUser1.size());
		
		System.out.println("[DONE]\n");
		
	}
	
	@Test
	@Order(10)
	@DisplayName("Listar task planedas por categoria (y atrasadas) sin terminar")
	void testListCategoryTask() {
		System.out.println();
		System.out.println("[TEST 10]");
		
		UserVO user1 = userService.findById(1);
		CategoryVO cat2 = catService.findById(2);
		CategoryVO cat3 = catService.findById(3);
		CategoryVO cat4 = catService.findById(4);
		CategoryVO cat5 = catService.findById(5);
		
		List<TaskVO> listTaskCat2 = taskService.listCategoryTask(user1, cat2);
		List<TaskVO> listTaskCat3 = taskService.listCategoryTask(user1, cat3);
		List<TaskVO> listTaskCat4 = taskService.listCategoryTask(user1, cat4);
		List<TaskVO> listTaskCat5 = taskService.listCategoryTask(user1, cat5);
		
		System.out.println("----");
		System.out.println("Tareas de la categoría 2:");
		for (TaskVO t2 : listTaskCat2) {
			System.out.println("- "+t2.getTitle()+" - Planned: "+t2.getPlanned());
		}
		System.out.println("----");
		System.out.println("Tareas de la categoría 3:");
		for (TaskVO t3 : listTaskCat3) {
			System.out.println("- "+t3.getTitle()+" - Planned: "+t3.getPlanned());
		}
		System.out.println("----");
		System.out.println("Tareas de la categoría 4:");
		for (TaskVO t4 : listTaskCat4) {
			System.out.println("- "+t4.getTitle()+" - Planned: "+t4.getPlanned());
		}
		System.out.println("----");
		System.out.println("Tareas de la categoría 5:");
		for (TaskVO t5 : listTaskCat5) {
			System.out.println("- "+t5.getTitle()+" - Planned: "+t5.getPlanned());
		}
		
		//sabemos que la categoria 5 tiene 5 tareas
		assertEquals(5,listTaskCat5.size());
		
		System.out.println("[DONE]\n");		
	}

		

}
