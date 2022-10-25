package com.esg.services.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerApplicationTests {

	@Test
	@DisplayName("Given empty parameter, when start Application, then execute successfully.")
	void contextLoads() {
		CustomerApplication.main(new String[] {});
	}

}
