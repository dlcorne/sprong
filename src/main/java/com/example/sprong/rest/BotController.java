package com.example.sprong.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprong.domain.Bots;
import com.example.sprong.service.BotsService;

@RestController
public class BotController {
	
	private BotsService service;
	
	@Autowired
	public BotController(BotsService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/hello")
	public String greeting() {
		return "Hello, world!";
	}
	
	@PostMapping("/createBot")
	public Bots makeBot(@RequestBody Bots bot) {
		System.out.println("Body: " + bot);
		return this.service.makeBot(bot);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/getAll")
	public List<Bots> getAllBots() {
		return this.service.getAllBots();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Bots> getById(@PathVariable int id) {
		System.out.println("ID: " + id);
		return new ResponseEntity<Bots>(this.service.getById(id), HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/update/{id}")
	public Bots updateBot(@PathVariable int id, @PathParam("name") String name, @PathParam("make") String make, @PathParam("age") Integer age, @PathParam("cost") int cost) {
		System.out.println("ID = " + id);
		System.out.println("Name = " + name);
		System.out.println("Make = " + make);
		System.out.println("Age = " + age);
		System.out.println("Cost = " + cost);
		return this.service.updateBot(id, name, make, age, cost);
	}
	
	@DeleteMapping("/remove/{id}")
	public void delete(@PathVariable int id) {
		System.out.println("ID = " + id);
		this.service.delete(id);
	}
	
}
