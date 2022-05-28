package com.evg3108.randompicker;

import org.junit.jupiter.api.Test;
import org.postgresql.Driver;
import org.springframework.boot.test.context.SpringBootTest;

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

	@Test
	void contextLoads() {
	}

}
