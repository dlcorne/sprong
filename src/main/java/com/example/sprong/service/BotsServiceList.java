package com.example.sprong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sprong.domain.Bots;

@Service
public class BotsServiceList implements BotsService {

	private List<Bots> bots;
	
	public BotsServiceList() {
		super();
		this.bots = new ArrayList<>();
		this.bots.add(new Bots("Tokyo Cruiser", "Brawler", 65, 500000));
	}

	@Override
	public Bots makeBot(Bots bot) {
		this.bots.add(bot);
		return bots.get(this.bots.size() - 1);
	}

	@Override
	public List<Bots> getAllBots() {
		return this.bots;
	}

	@Override
	public Bots getById(int id) {
		return this.bots.get(id);
	}

	@Override
	public Bots updateBot(int id, String name, String make, Integer age, int cost) {
		Bots toUpdate = this.bots.get(id);

		if (name != null && !name.isBlank())
			toUpdate.setName(name);
		if (make != null && !name.isBlank())
			toUpdate.setMake(make);
		if (age != null)
			toUpdate.setAge(age);
		if (cost != 0)
			toUpdate.setCost(cost);

		return toUpdate;
	}

	@Override
	public void delete(int id) {
		this.bots.remove(id);
	}

}