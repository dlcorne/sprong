package com.example.sprong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.sprong.rest.BotController;
import com.example.sprong.service.BotsService;

@SpringBootApplication
public class SprongApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SprongApplication.class, args);
		System.out.println("Service: " + context.getBean(BotsService.class));
		System.out.println("Controller: " + context.getBean(BotController.class));
	}

}
