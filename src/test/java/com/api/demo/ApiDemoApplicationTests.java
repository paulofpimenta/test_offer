package com.api.demo;

import com.api.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiDemoApplicationTests {

	@Value(value="${local.server.port}")
	private int port;
	@Autowired
	private UserController userController;

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	@Test
	public void shouldReturnRightPort() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/user/",
				String.class)).contains("Welcome to the user demo API");
	}

}
