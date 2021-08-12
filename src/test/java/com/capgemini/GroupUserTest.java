package com.capgemini;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.service.GroupUserService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para GroupUserVO")
class GroupUserTest {
	
	@Autowired
	private GroupUserService groupUserService;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
