package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		userService.add(new UserVO("usuario3", "usuario3@capgemini.com", "usuario3", UserStatus.DISABLED, false,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>()));
		userService.add(new UserVO("usuario4", "usuario4@capgemini.com", "usuario4", UserStatus.DISABLED, false,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>()));
		userService.add(new UserVO("usuario5", "usuario5@capgemini.com", "usuario5", UserStatus.DISABLED, false,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>()));

		assertEquals(5, userService.listAll().size());

		System.out.println();
		System.out.println("________");
	}

	@Test
	@Order(2)
	@DisplayName("Borrar usuarios")
	void testDeletetUser() {
		System.out.println();
		System.out.println("[TEST 2]");

		UserVO user = userService.findById(2);

		userService.delete(user);

		assertEquals(4, userService.listAll().size());

		System.out.println();
		System.out.println("________");
	}

	@Test
	@Order(3)
	@DisplayName("Borrar usuarios pasandole su id")
	void testDeletetUserById() {
		System.out.println();
		System.out.println("[TEST 3]");

		userService.deleteById(3);

		assertEquals(3, userService.listAll().size());

		System.out.println();
		System.out.println("________");
	}

	@Test
	@Order(4)
	@DisplayName("Buscar todos los usuarios")
	void testListAll() {
		System.out.println();
		System.out.println("[TEST 4]");

		List<UserVO> usuarios = userService.listAll();
		
		System.out.println("Los usuarios de nuestra BBDD son:");
		
		for (UserVO u : usuarios) {
			System.out.println(u.getLogin());
		}
		
		assertEquals(3, userService.listAll().size());

		System.out.println();
		System.out.println("________");
	}


}
