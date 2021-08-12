package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.model.GroupUserVO;
import com.capgemini.model.GroupVO;
import com.capgemini.model.TaskVO;
import com.capgemini.service.GroupService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para GroupVO")
class GroupTest {

	@Autowired
	private GroupService groupService;

	@Test
	@Order(1)
	@DisplayName("Insertar grupos")
	void testInsertGroup() {
		System.out.println();
		System.out.println("[TEST 1]");

		groupService.add(new GroupVO("grupo1", "grupo1 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>()));
		groupService.add(new GroupVO("grupo2", "grupo2 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>()));
		groupService.add(new GroupVO("grupo3", "grupo3 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>()));
		groupService.add(new GroupVO("grupo4", "grupo4 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>()));
		groupService.add(new GroupVO("grupo5", "grupo5 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>()));

		assertEquals(5, groupService.listAll().size());
		
		System.out.println();
		System.out.println("________");

	}
	
	@Test
	@Order(2)
	@DisplayName("Borrar grupos")
	void testDeletetGroup() {
		System.out.println();
		System.out.println("[TEST 2]");
		
		GroupVO group= groupService.findById(2);
		
		groupService.delete(group);
		
		assertEquals(4, groupService.listAll().size());		
		
		System.out.println();
		System.out.println("________");
		
	}
	
	@Test
	@Order(3)
	@DisplayName("Borrar grupos pasandole su id")
	void testDeletetGroupById() {
		System.out.println();
		System.out.println("[TEST 3]");
		
		groupService.deleteById(3);
		
		assertEquals(3, groupService.listAll().size());
		
		System.out.println();
		System.out.println("________");
	}
	
	@Test
	@Order(4)
	@DisplayName("Buscar todos los grupos")
	void testListAllGroups() {
		System.out.println();
		System.out.println("[TEST 5]");
		
		List<GroupVO> grupos = groupService.listAll();
		
		System.out.println("Los grupos de nuestra BBDD son:");
		
		for (GroupVO g : grupos) {
			System.out.println(g.getName());
		}
		
		assertEquals(3, groupService.listAll().size());
		
		
		System.out.println();
		System.out.println("________");		
	}
	
	@Test
	@Order(5)
	@DisplayName("Buscar grupo por id")
	void testFindGroupById() {
		System.out.println();
		System.out.println("[TEST 5]");
		
		System.out.println("El grupo con id[1] es: "+groupService.findById(1).getName());
		
		assertEquals("grupo1", groupService.findById(1).getName());
		
		System.out.println();
		System.out.println("________");
		
	}
	
	@Test
	@Order(6)
	@DisplayName("Modificar grupos")
	void testModifyGroup() {
		System.out.println();
		System.out.println("[TEST 6]");
		
		GroupVO group= groupService.findById(1);
		
		System.out.println("El grupo con id[1] es: "+groupService.findById(1).getName());
		
		group.setName("grupo1 - modificado");
		
		groupService.modify(group);
		
		System.out.println("Tras los cambios");
		System.out.println("El grupo con id[1] es: "+groupService.findById(1).getName());
		
		assertEquals("grupo1 - modificado",groupService.findById(1).getName());
		
		
		System.out.println();
		System.out.println("________");
		
	}

}
