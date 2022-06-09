package com.evg3108.randompicker;

import com.evg3108.randompicker.model.Group;
import com.evg3108.randompicker.repository.GroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.postgresql.Driver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RandompickerApplicationTests {

	@Test
	void dbConnectionEstablishes() throws ClassNotFoundException {
		try {
			Class.forName(Driver.class.getName());
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
			throw e;
		}
	}

	//temporary test to check if liquibase is working
	@Test
	void checkDbInit() {
		List<Group> list = GroupRepository.getListOfGroups();
		Assertions.assertFalse(list.isEmpty());
	}

	@Test
	void contextLoads() {
	}

}
