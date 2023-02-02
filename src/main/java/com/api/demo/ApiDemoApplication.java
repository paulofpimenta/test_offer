package com.api.demo;

import com.api.demo.controller.UserController;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class ApiDemoApplication {

	private UserController userController;



	public static void main(String[] args) {

		SpringApplication.run(ApiDemoApplication.class, args);
	}


}