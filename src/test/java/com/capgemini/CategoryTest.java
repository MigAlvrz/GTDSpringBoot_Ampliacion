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
import com.capgemini.service.CategoryService;
import com.capgemini.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para CategoryVO")
class CategoryTest {

	@Autowired
	private CategoryService catService;

	@Autowired
	private UserService userService;

	@Test
	@Order(1)
	@DisplayName("Insertar categorias")
	void testInsertCategory() {
		System.out.println();
		System.out.println("[TEST 1]");

		UserVO user = new UserVO("usuario1", "usuario1@capgemini.com", "usuario1", UserStatus.ENABLED, true,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>());
		userService.add(user);// necesario para poder añadir categorias

		catService.add(new CategoryVO("categoria1", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria2", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria3", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria4", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria5", new ArrayList<TaskVO>(), user));

		assertEquals(5, catService.listAll().size());

		System.out.println();
		System.out.println("________");
	}

	@Test
	@Order(2)
	@DisplayName("Borrar categorias")
	void testDeletetCategory() {
		System.out.println();
		System.out.println("[TEST 2]");

		CategoryVO category = catService.findById(2);
		catService.delete(category);
		assertEquals(4, catService.listAll().size());

		System.out.println();
		System.out.println("________");

	}
	
	@Test
	@Order(3)
	@DisplayName("Borrar categorias pasandole su id")
	void testDeletetCategoryById() {
		System.out.println();
		System.out.println("[TEST 3]");
		
		catService.deleteById(3);
		assertEquals(3, catService.listAll().size());
		
		
		System.out.println();
		System.out.println("________");
	}
	
	@Test
	@Order(4)
	@DisplayName("Buscar todos las categorias")
	void testListAllCategories() {
		System.out.println();
		System.out.println("[TEST 4]");
		
		List<CategoryVO> categorias=catService.listAll();
		System.out.println("Las categorias de nuestra BBDD son: ");
		
		for (CategoryVO c : categorias) {
			System.out.println(c.getName());
		}
		
		assertEquals(3, categorias.size());
		
		System.out.println();
		System.out.println("________");		
	}
	
	@Test
	@Order(5)
	@DisplayName("Buscar categorias por id")
	void testFindCategoriesById() {
		System.out.println();
		System.out.println("[TEST 5]");
		
		System.out.println("La categoria con id[1] es: "+catService.findById(1).getName());
		
		assertEquals("categoria1", catService.findById(1).getName());
		
		System.out.println();
		System.out.println("________");
		
	}

}
