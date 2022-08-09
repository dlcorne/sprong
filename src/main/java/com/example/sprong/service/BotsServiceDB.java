package com.example.sprong.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.sprong.domain.Bots;
import com.example.sprong.repos.BotsRepo;

@Service
@Primary
public class BotsServiceDB implements BotsService {
	
	private BotsRepo repo;
	
	public BotsServiceDB(BotsRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Bots makeBot(Bots bot) {
		return this.repo.save(bot);
	}
	
	@Override
	public List<Bots> getAllBots() {
		return this.repo.findAll();
	}
	
	@Override
	public Bots getById(int id) {
		return this.repo.findById(id).get();
	}
	
	@Override
	public Bots updateBot(int id, String name, String make, Integer age, int cost) {
		Bots toUpdate = this.getById(id);

		if (name != null && !name.isBlank())
			toUpdate.setName(name);
		if (make != null && !name.isBlank())
			toUpdate.setMake(make);
		if (age != null)
			toUpdate.setAge(age);
		if (cost != 0)
			toUpdate.setCost(cost);

		return this.repo.save(toUpdate);
	}
	
	@Override
	public void delete(int id) {
		this.repo.deleteById(id);
	}
	
}

