package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;

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

}
