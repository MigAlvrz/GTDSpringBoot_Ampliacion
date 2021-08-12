package com.capgemini;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.capgemini.service.UserService;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para UserVO")
class UserTest {
	
	@Autowired
	static UserService userService;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
