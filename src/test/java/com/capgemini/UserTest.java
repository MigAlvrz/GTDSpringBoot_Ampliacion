package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

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
import com.capgemini.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para UserVO")
class UserTest {

	@Autowired
	private UserService userService;

	@Test
	@Order(1)
	@DisplayName("Insertar usuarios")
	void testInsertUser() {
		System.out.println();
		System.out.println("[TEST 1]");

		userService.add(new UserVO("usuario1", "usuario1@capgemini.com", "usuario1", UserStatus.ENABLED, true,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>()));
		userService.add(new UserVO("usuario2", "usuario2@capgemini.com", "usuario2", UserStatus.DISABLED, false,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>()));
		
		assertEquals(2, userService.listAll().size());
		
		System.out.println();
		System.out.println("________");
	}

}
