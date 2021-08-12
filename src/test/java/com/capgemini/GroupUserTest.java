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

import com.capgemini.model.CategoryVO;
import com.capgemini.model.GroupUserVO;
import com.capgemini.model.GroupVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserStatus;
import com.capgemini.model.UserVO;
import com.capgemini.service.GroupService;
import com.capgemini.service.GroupUserService;
import com.capgemini.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para GroupUserVO")
class GroupUserTest {

	@Autowired
	private GroupUserService groupUserService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Test
	@Order(1)
	@DisplayName("Insertar groupuser")
	void testInsertGroupUser() {
		System.out.println();
		System.out.println("[TEST 1]");

		// necesario crear un usuario y varios grupos
		UserVO user = new UserVO("usuario1", "usuario1@capgemini.com", "usuario1", UserStatus.ENABLED, true,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>());
		
		//añadimos el user
		userService.add(user);

		GroupVO group1 = new GroupVO("grupo1", "grupo1 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group2 = new GroupVO("grupo2", "grupo2 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group3 = new GroupVO("grupo3", "grupo3 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group4 = new GroupVO("grupo4", "grupo4 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group5 = new GroupVO("grupo5", "grupo5 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		
		//añadimos los grupos
		groupService.add(group1);
		groupService.add(group2);
		groupService.add(group3);
		groupService.add(group4);
		groupService.add(group5);

		groupUserService.add(new GroupUserVO(true, user, group1));
		groupUserService.add(new GroupUserVO(false, user, group2));
		groupUserService.add(new GroupUserVO(false, user, group3));
		groupUserService.add(new GroupUserVO(true, user, group4));
		groupUserService.add(new GroupUserVO(false, user, group5));
		
		assertEquals(5, groupUserService.listAll().size());
		
		
		System.out.println();
		System.out.println("________");
	}

}
