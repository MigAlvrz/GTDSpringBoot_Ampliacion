package com.capgemini;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.service.GroupService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para GroupVO")
class GroupTest {
	
	@Autowired
	private GroupService groupService;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
