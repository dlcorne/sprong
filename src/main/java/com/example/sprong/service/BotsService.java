package com.example.sprong.service;

import java.util.List;

import com.example.sprong.domain.Bots;

public interface BotsService {
	
	Bots makeBot(Bots bot);
	
	List<Bots> getAllBots();
	
	Bots getById(int id);
	
	Bots updateBot(int id, String name, String make, Integer age, int cost);
	
	void delete(int id);

}
